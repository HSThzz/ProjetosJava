package com.example.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter //injeçao do lombock para evitar o uso de getter e setter na classe, gera em tempo de compilaçao
@NoArgsConstructor
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true) //um autor para muitos livros
    private List<Livro> livros;

    /*public Autor(){
        //para poder usar o jpa
    }*/

    public Autor(String nome, LocalDate dataNascimento, String nacionalidade){
        this.nome = nome;
        this.dataNascimento= dataNascimento;
        this.nacionalidade = nacionalidade;
    }

}
