package com.example.estoque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idcategoria;

    @NotNull
    @Column(nullable = false)
    public String nome_cat;


    public Categoria() {
    }

    public Categoria(long idcategoria, String nome_cat) {
        this.idcategoria = idcategoria;
        this.nome_cat = nome_cat;
    }

    public Categoria(String nome_cat) {
        this.nome_cat = nome_cat;
    }

    public long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNome_cat() {
        return nome_cat;
    }

    public void setNome_cat(String nome_cat) {
        this.nome_cat = nome_cat;
    }
}
