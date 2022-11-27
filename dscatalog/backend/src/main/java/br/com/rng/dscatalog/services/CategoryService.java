package br.com.rng.dscatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.repositories.CategoryRepository;
import br.com.rng.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

   @Autowired
   private CategoryRepository categoryRepository;

   @Transactional(readOnly = true)
   public List<CategoryDTO> findAll() {
      List<Category> categories = categoryRepository.findAll();

      return categories.stream().map(category -> new CategoryDTO(category)).toList();
   }

   @Transactional(readOnly = true)
   public CategoryDTO findById(Long id) {
      Optional<Category> category = categoryRepository.findById(id);

      return new CategoryDTO(category.orElseThrow(() -> new EntityNotFoundException("Entity not found!")));
   }

   @Transactional
   public CategoryDTO insert(CategoryDTO categoryDTO) {

      Category category = new Category();
      category.setName(categoryDTO.getName());
      category = categoryRepository.save(category);

      return new CategoryDTO(category);
   }
}