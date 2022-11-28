package br.com.rng.dscatalog.resources;

import java.net.URI;

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

import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

   @Autowired
   private ProductService productService;

   @GetMapping
   public ResponseEntity<Page<ProductDTO>> findAll(
         @RequestParam(name = "page", defaultValue = "0") Integer page,
         @RequestParam(name = "linesPerPage", defaultValue = "10") Integer linesPerPage,
         @RequestParam(name = "orderBy", defaultValue = "name") String orderBy,
         @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

      PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
      Page<ProductDTO> pageProduct = productService.findAllPaged(pageRequest);

      return ResponseEntity.ok(pageProduct);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

      ProductDTO productDTO = productService.findById(id);

      return ResponseEntity.ok(productDTO);
   }

   @PostMapping
   public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {
      productDTO = productService.insert(productDTO);

      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDTO.getId())
            .toUri();

      return ResponseEntity.created(uri).body(productDTO);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

      productDTO = productService.update(id, productDTO);

      return ResponseEntity.ok(productDTO);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      productService.delete(id);

      return ResponseEntity.noContent().build();
   }
}