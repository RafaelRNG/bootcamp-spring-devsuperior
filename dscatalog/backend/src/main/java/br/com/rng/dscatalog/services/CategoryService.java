package br.com.rng.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

   @Autowired
   private CategoryRepository categoryRepository;

   @Transactional(readOnly = true)
   public List<CategoryDTO> findAll() {
      List<Category> categories = categoryRepository.findAll();

      return categories.stream().map(category -> new CategoryDTO(category)).toList();
   }
}