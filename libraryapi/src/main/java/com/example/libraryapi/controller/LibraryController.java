package com.example.libraryapi.controller;


import com.example.libraryapi.model.Autor;
import com.example.libraryapi.model.Livro;
import com.example.libraryapi.Service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;


    @PostMapping
    public ResponseEntity<String> adicionarAutor(@RequestBody Autor autor){

        Autor novoAutor =  libraryService.adicionarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/livros/{id}")
    public ResponseEntity<String> adicionarLivro(@PathVariable UUID id, @RequestBody Livro livro) throws Exception {
        Livro novoLivro = libraryService.adicionarLivro(id, livro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{titulo}")
    public ResponseEntity<String> apagarLivro(@PathVariable String titulo){
        libraryService.apagarLivro(titulo);
        return ResponseEntity.status(200).body("Livro deletado com sucesso");
    }

    //@Deprecated
    @GetMapping("/{id}")
    public List<Livro> getLivros(@PathVariable UUID id){
        return libraryService.getLivros(id);
    }
}
