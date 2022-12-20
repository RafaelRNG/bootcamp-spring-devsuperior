package br.com.rng.dscatalog.services;

import br.com.rng.dscatalog.dto.CategoryDTO;
import br.com.rng.dscatalog.dto.ProductDTO;
import br.com.rng.dscatalog.dto.RoleDTO;
import br.com.rng.dscatalog.dto.UserDTO;
import br.com.rng.dscatalog.entities.Category;
import br.com.rng.dscatalog.entities.Product;
import br.com.rng.dscatalog.entities.Role;
import br.com.rng.dscatalog.entities.User;
import br.com.rng.dscatalog.repositories.CategoryRepository;
import br.com.rng.dscatalog.repositories.ProductRepository;
import br.com.rng.dscatalog.repositories.RoleRepository;
import br.com.rng.dscatalog.repositories.UserRepository;
import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Transactional(readOnly = true)
   public Page<UserDTO> findAllPaged(Pageable pageable) {
      Page<User> users = userRepository.findAll(pageable);

      return users.map(user -> new UserDTO(user));
   }

   @Transactional(readOnly = true)
   public UserDTO findById(Long id) {
      User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));

      return new UserDTO(user);
   }

   @Transactional
   public UserDTO insert(UserDTO userDTO) {

      User user = new User();
      copyDtoToEntity(userDTO, user);
      user = userRepository.save(user);

      return new UserDTO(user);
   }

   @Transactional
   public UserDTO update(Long id, UserDTO userDTO) {
      try {
         User user = userRepository.getReferenceById(id);
         copyDtoToEntity(userDTO, user);
         user = userRepository.save(user);

         return new UserDTO(user);

      } catch (EntityNotFoundException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      }
   }

   public void delete(Long id) {
      try {
         userRepository.deleteById(id);
      }
      catch (EmptyResultDataAccessException e) {
         throw new ResourceNotFoundException("Id not found: " + id);
      }
      catch (DataIntegrityViolationException e) {
         throw new DatabaseException("Integrity violation!");
      }
   }

   private void copyDtoToEntity(UserDTO userDTO, User user) {
      user.setFirstName(userDTO.getFirstName());
      user.setLastName(userDTO.getLastName());
      user.setEmail(userDTO.getEmail());

      user.getRoles().clear();
      for(RoleDTO roleDTO: userDTO.getRoleDTOS()) {
         Role role = roleRepository.getReferenceById(roleDTO.getId());
         user.getRoles().add(role);
      }
   }
}