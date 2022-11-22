package br.com.rng.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

   @Autowired
   private CategoryService categoryService;

   @GetMapping
   public ResponseEntity<List<Category>> findAll() {

      return ResponseEntity.ok(categoryService.findAll());
   }
}