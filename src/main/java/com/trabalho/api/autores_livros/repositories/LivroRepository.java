package com.trabalho.api.autores_livros.repositories;


import com.trabalho.api.autores_livros.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByAutorIsNull();
}
