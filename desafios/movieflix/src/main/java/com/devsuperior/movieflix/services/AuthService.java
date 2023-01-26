package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User userAuthenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = this.userRepository.findByEmail(email);

            return user;
        } catch (Exception e) {
            throw new ObjNotFoundException("User not found!");
        }
    }


    public void visitorOrMember(Long id) {
        User user = this.userAuthenticated();

        if(!user.getId().equals(id) && !user.getRoles().containsAll(Arrays.asList("VISITOR", "MEMBER"))) {
            throw new ForbiddenException("Access denied!");
        }
    }
}