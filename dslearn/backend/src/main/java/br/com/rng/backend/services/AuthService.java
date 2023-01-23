package br.com.rng.backend.services;

import br.com.rng.backend.entities.User;
import br.com.rng.backend.repositories.UserRepository;
import br.com.rng.backend.services.exceptions.ForbiddenException;
import br.com.rng.backend.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User authenticated() {
      try {
          String email = SecurityContextHolder.getContext().getAuthentication().getName();
          return userRepository.findByEmail(email);
      }
      catch(Exception e) {
         throw new UnauthorizedException("Invalid user!");
      }
    }

    public void validateSelOrAdmin(Long userId) {
        User user = authenticated();

        if(!user.getId().equals(userId) && !user.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Access denied!");
        }
    }
}