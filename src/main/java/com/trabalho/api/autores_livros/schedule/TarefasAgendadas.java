package com.trabalho.api.autores_livros.schedule;

import com.trabalho.api.autores_livros.models.Livro;
import com.trabalho.api.autores_livros.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

    @Component
    public class TarefasAgendadas {

        @Autowired
        private LivroRepository livroRepository;

        @Scheduled(fixedRate = 60000)
        public void removerLivrosOrfaos() {
            System.out.println("SCHEDULE RODANDO...");
            List<Livro> livrosOrfaos = livroRepository.findByAutorIsNull();

            if (!livrosOrfaos.isEmpty()) {
                livroRepository.deleteAll(livrosOrfaos);
                System.out.println("Livros órfãos removidos: " + livrosOrfaos.size());
            }
        }
    }

