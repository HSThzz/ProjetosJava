package com.example.libraryapi.Service;

import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.repository.AutorRepository;
import com.example.libraryapi.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Autor adicionarAutor(Autor autor){
        if(autorRepository.findByNome(autor.getNome()).isEmpty()){
            for(Livro livro : autor.getLivros()){
                livro.setAutor(autor);
            }
        }
        return autorRepository.save(autor);
    }

    public Livro adicionarLivro(UUID id, Livro livro) throws Exception {
        if(livroRepository.findByTitulo(livro.getTitulo()).isPresent()){
            throw new Exception("Livro ja existe");
        }

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new Exception("Autor não encontrado"));

        livro.setAutor(autor);

        autor.getLivros().add(livro);

        return livroRepository.save(livro);
    }

    @Transactional
    public void apagarLivro(String titulo){
        livroRepository.deleteByTitulo(titulo);
    }

    public List<Livro> getLivros(UUID id){
        return autorRepository.getLivrosById(id);
    }
}
