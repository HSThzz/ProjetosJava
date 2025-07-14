package com.example.libraryapi.repository;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    @Query("SELECT a.livros FROM Autor a WHERE a.id = :id")
    public List<Livro> getLivrosById(UUID id);

    public Optional<Autor> findById(UUID id);

    public Optional<Autor> findByNome(String nome);
}
