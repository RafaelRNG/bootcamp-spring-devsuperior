package br.com.rng.backend.services;

import br.com.rng.backend.dtos.UserDTO;
import br.com.rng.backend.entities.User;
import br.com.rng.backend.repositories.UserRepository;
import br.com.rng.backend.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            logger.error("User not found: " + email);
            throw new UsernameNotFoundException("E-mail not found!");
        }

        logger.info("User found: " + email);
        return user;
    }

}