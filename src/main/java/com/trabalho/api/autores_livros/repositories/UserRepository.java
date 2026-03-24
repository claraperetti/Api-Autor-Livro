package com.trabalho.api.autores_livros.repositories;

import com.trabalho.api.autores_livros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByApiKey(String apiKey);
    Optional<User> findByEmail(String email);
}
