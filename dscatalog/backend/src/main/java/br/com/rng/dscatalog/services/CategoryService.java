package br.com.rng.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.repositories.CategoryRepository;
import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

   @Autowired
   private CategoryRepository categoryRepository;

   @Transactional(readOnly = true)
   public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
      Page<Category> categories = categoryRepository.findAll(pageRequest);

      return categories.map(category -> new CategoryDTO(category));
   }

   @Transactional(readOnly = true)
   public CategoryDTO findById(Long id) {
      Optional<Category> category = categoryRepository.findById(id);

      return new CategoryDTO(category.orElseThrow(() -> new ResourceNotFoundException("Entity not found!")));
   }

   @Transactional
   public CategoryDTO insert(CategoryDTO categoryDTO) {

      Category category = new Category();
      category.setName(categoryDTO.getName());
      category = categoryRepository.save(category);

      return new CategoryDTO(category);
   }

   @Transactional
   public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
      try {
         Category category = categoryRepository.getReferenceById(id);
         category.setName(categoryDTO.getName());
         category = categoryRepository.save(category);

         return new CategoryDTO(category);

      } catch (EntityNotFoundException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      }
   }

   public void delete(Long id) {
      try {
         categoryRepository.deleteById(id);
      } catch (EmptyResultDataAccessException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      }
   }
}