package com.trabalho.api.autores_livros.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Autor autor;
}



