package br.com.rng.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

   @Autowired
   private CategoryRepository categoryRepository;

   public List<Category> findAll() {
      return categoryRepository.findAll();
   }
}