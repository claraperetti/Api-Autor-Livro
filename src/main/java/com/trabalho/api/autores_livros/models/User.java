package com.trabalho.api.autores_livros.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @NotBlank(message = "O Email é obrigatório")
    @Email(message = "O email precisa estar no formato nome@email.com")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    private String apiKey;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
