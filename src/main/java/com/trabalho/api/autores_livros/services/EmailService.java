package com.trabalho.api.autores_livros.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Async
    public void sendWelcomeEmail(String email) {
        try {
            System.out.println("Enviando email para: " + email);

            Thread.sleep(5000);

            System.out.println("Email enviado com sucesso para: " + email);
        } catch (InterruptedException e) {
            System.out.println("Erro ao enviar email para: " + email);
            Thread.currentThread().interrupt();
        }
    }
}
