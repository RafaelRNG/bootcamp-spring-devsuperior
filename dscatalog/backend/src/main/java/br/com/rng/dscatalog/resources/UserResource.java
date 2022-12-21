package br.com.rng.dscatalog.resources;

import br.com.rng.dscatalog.dtos.UserDTO;
import br.com.rng.dscatalog.dtos.UserInsertDTO;
import br.com.rng.dscatalog.dtos.UserUpdateDTO;
import br.com.rng.dscatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserResource {

   @Autowired
   private UserService userService;

   @GetMapping
   public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {

      Page<UserDTO> pageUser = userService.findAllPaged(pageable);

      return ResponseEntity.ok(pageUser);
   }

   @GetMapping("/{id}")
   public ResponseEntity<UserDTO> findById(@PathVariable Long id) {

      UserDTO userDTO = userService.findById(id);

      return ResponseEntity.ok(userDTO);
   }

   @PostMapping
   public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserInsertDTO userInsertDTO) {
      UserDTO userDTO = userService.insert(userInsertDTO);

      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId())
            .toUri();

      return ResponseEntity.created(uri).body(userDTO);
   }

   @PutMapping("/{id}")
   public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {

      UserDTO userDTO = userService.update(id, userUpdateDTO);

      return ResponseEntity.ok(userDTO);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      userService.delete(id);

      return ResponseEntity.noContent().build();
   }
}