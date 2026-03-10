package com.trabalho.api.autores_livros.controllers;

import com.trabalho.api.autores_livros.dto.RequestUser;
import com.trabalho.api.autores_livros.dto.ResponseUser;
import com.trabalho.api.autores_livros.models.User;
import com.trabalho.api.autores_livros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUser> registerUser(@RequestBody RequestUser user){
       ResponseUser responseUser =  userService.createUser(user);
       return ResponseEntity.ok(responseUser);
    }
}
