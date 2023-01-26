package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/profile")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<UserDTO> findUser() {

        UserDTO userDTO = this.userService.findUser();

        return ResponseEntity.ok().body(userDTO);
    }
}