package br.com.rng.dscatalog.services;

import javax.persistence.EntityNotFoundException;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.entities.Product;
import br.com.rng.dscatalog.repositories.ProductRepository;
import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private CategoryRepository categoryRepository;

   @Transactional(readOnly = true)
   public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
      Page<Product> products = productRepository.findAll(pageRequest);

      return products.map(product -> new ProductDTO(product, product.getCategories()));
   }

   @Transactional(readOnly = true)
   public ProductDTO findById(Long id) {
      Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));

      return new ProductDTO(product, product.getCategories());
   }

   @Transactional
   public ProductDTO insert(ProductDTO productDTO) {

      Product product = new Product();
      copyDtoToEntity(productDTO, product);
      product = productRepository.save(product);

      return new ProductDTO(product);
   }

   @Transactional
   public ProductDTO update(Long id, ProductDTO productDTO) {
      try {
         Product product = productRepository.getReferenceById(id);
         copyDtoToEntity(productDTO, product);
         product = productRepository.save(product);

         return new ProductDTO(product);

      } catch (EntityNotFoundException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      }
   }

   public void delete(Long id) {
      try {
         productRepository.deleteById(id);
      } catch (EmptyResultDataAccessException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      } catch (DataIntegrityViolationException e) {
         throw new DatabaseException("Integrity violation!");
      }
   }

   private void copyDtoToEntity(ProductDTO productDTO, Product product) {
      product.setName(productDTO.getName());
      product.setDescription(productDTO.getDescription());
      product.setDate(productDTO.getDate());
      product.setImgUrl(productDTO.getImgUrl());
      product.setPrice(productDTO.getPrice());

      product.getCategories().clear();
      for (CategoryDTO categoryDTO : productDTO.getCategories()) {
         Category category = categoryRepository.getReferenceById(categoryDTO.getId());
         product.getCategories().add(category);
      }
   }
}
