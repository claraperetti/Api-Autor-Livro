package com.trabalho.api.autores_livros.repositories;

import com.trabalho.api.autores_livros.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
