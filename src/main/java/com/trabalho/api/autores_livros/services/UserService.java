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

    private String generateApiKey(){
        return UUID.randomUUID().toString();
    }

    public ResponseUser createUser(RequestUser userContent){
        //evitar email duplicado
        if(userRepository.findByEmail(userContent.email()).isPresent()){
            throw new RuntimeException("E-mail ja cadastrado em outro user");
        }
        User user = new User(userContent.email(), userContent.password());
        user.setApiKey(generateApiKey());

        User savedUser = userRepository.save(user);

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
