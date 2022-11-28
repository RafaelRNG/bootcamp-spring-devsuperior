package br.com.rng.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

   @Autowired
   private CategoryService categoryService;

   @GetMapping
   public ResponseEntity<Page<CategoryDTO>> findAll(
         @RequestParam(name = "page", defaultValue = "0") Integer page,
         @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
         @RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
         @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
      Page<CategoryDTO> pageCategory = categoryService.findAllPaged(pageRequest);

      return ResponseEntity.ok(pageCategory);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {

      CategoryDTO categoryDTO = categoryService.findById(id);

      return ResponseEntity.ok(categoryDTO);
   }

   @PostMapping
   public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO categoryDTO) {
      categoryDTO = categoryService.insert(categoryDTO);

      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryDTO.getId())
            .toUri();

      return ResponseEntity.created(uri).body(categoryDTO);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {

      categoryDTO = categoryService.update(id, categoryDTO);

      return ResponseEntity.ok(categoryDTO);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      categoryService.delete(id);

      return ResponseEntity.noContent().build();
   }
}