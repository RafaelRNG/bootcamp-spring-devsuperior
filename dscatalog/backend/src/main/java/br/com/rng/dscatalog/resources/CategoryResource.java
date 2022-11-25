package br.com.rng.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

   @Autowired
   private CategoryService categoryService;

   @GetMapping
   public ResponseEntity<List<CategoryDTO>> findAll() {

      return ResponseEntity.ok(categoryService.findAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {

      CategoryDTO categoryDTO = categoryService.findById(id);

      return ResponseEntity.ok(categoryDTO);
   }
}