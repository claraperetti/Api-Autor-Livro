package com.trabalho.api.autores_livros.services;

import com.trabalho.api.autores_livros.dto.RequestUser;
import com.trabalho.api.autores_livros.dto.ResponseUser;
import com.trabalho.api.autores_livros.models.User;
import com.trabalho.api.autores_livros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseUser createUser(RequestUser userContent){
        User user = new User(userContent.email(), userContent.password());
        User savedUser = userRepository.save(user);

        return new ResponseUser(savedUser.getEmail(), savedUser.getId(), savedUser.getApiKey());
    }



}
