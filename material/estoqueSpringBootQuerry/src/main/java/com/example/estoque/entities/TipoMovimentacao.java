package com.example.estoque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class TipoMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idtipo;

    @Column(nullable = false)
    @NotNull
    private String descricao;


    public TipoMovimentacao() {
    }

    public TipoMovimentacao(long idtipo, String descricao) {
        this.idtipo = idtipo;
        this.descricao = descricao;
    }

    public TipoMovimentacao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(long idtipo) {
        this.idtipo = idtipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
