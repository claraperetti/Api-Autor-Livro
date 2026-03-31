package com.trabalho.api.autores_livros.services;

import com.trabalho.api.autores_livros.dto.RequestUser;
import com.trabalho.api.autores_livros.dto.ResponseUser;
import com.trabalho.api.autores_livros.models.User;
import com.trabalho.api.autores_livros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private String generateApiKey(){
        return UUID.randomUUID().toString();
    }

    public ResponseUser createUser(RequestUser userContent){
        if(userRepository.findByEmail(userContent.email()).isPresent()){
            throw new RuntimeException("Email ja cadastrado!");
        }

        System.out.println("Cadastrando user: " + userContent.email());

        User user = new User(userContent.email(), userContent.password());
        user.setApiKey(generateApiKey());

        User savedUser = userRepository.save(user);

        System.out.println("User salvo com sucesso: " + savedUser.getEmail());

        emailService.sendWelcomeEmail(savedUser.getEmail());

        System.out.println("Resposta do cadastro retornada para: " + savedUser.getEmail());

        return new ResponseUser(savedUser.getEmail(), savedUser.getId(), savedUser.getApiKey());
    }

    public List<ResponseUser> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new ResponseUser(
                        user.getEmail(),
                        user.getId(),
                        user.getApiKey()
                ))
                .toList();
    }


}