package br.com.rng.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

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

   @Transactional(readOnly = true)
   public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
      Page<Product> categories = productRepository.findAll(pageRequest);

      return categories.map(Product -> new ProductDTO(Product));
   }

   @Transactional(readOnly = true)
   public ProductDTO findById(Long id) {
      Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));

      return new ProductDTO(product, product.getCategories());
   }

   @Transactional
   public ProductDTO insert(ProductDTO ProductDTO) {

      Product Product = new Product();
      // Product.setName(ProductDTO.getName());
      Product = productRepository.save(Product);

      return new ProductDTO(Product);
   }

   @Transactional
   public ProductDTO update(Long id, ProductDTO ProductDTO) {
      try {
         Product Product = productRepository.getReferenceById(id);
         // Product.setName(ProductDTO.getName());
         Product = productRepository.save(Product);

         return new ProductDTO(Product);

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
}