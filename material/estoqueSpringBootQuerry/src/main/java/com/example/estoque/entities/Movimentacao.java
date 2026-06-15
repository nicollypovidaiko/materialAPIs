package com.example.estoque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idmovimentacao;

    @Column(name = "dt_movimentacao", nullable = false)
    @NotNull
    private LocalDateTime dtmovimentacao;

    @Column(nullable = false)
    @NotNull
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "idmaterial",nullable = false)
    @NotNull
    private Material material;

    @ManyToOne
    @JoinColumn(name = "idtipo",nullable = false)
    @NotNull
    private TipoMovimentacao tipoMovimentacao;


    public Movimentacao() {
    }


    public Movimentacao(long idmovimentacao, LocalDateTime dtmovimentacao, int quantidade, Material material, TipoMovimentacao tipoMovimentacao) {
        this.idmovimentacao = idmovimentacao;
        this.dtmovimentacao = dtmovimentacao;
        this.quantidade = quantidade;
        this.material = material;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Movimentacao(LocalDateTime dtmovimentacao, int quantidade, Material material, TipoMovimentacao tipoMovimentacao) {
        this.dtmovimentacao = dtmovimentacao;
        this.quantidade = quantidade;
        this.material = material;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public long getIdmovimentacao() {
        return idmovimentacao;
    }

    public void setIdmovimentacao(long idmovimentacao) {
        this.idmovimentacao = idmovimentacao;
    }

    public LocalDateTime getDtmovimentacao() {
        return dtmovimentacao;
    }

    public void setDtmovimentacao(LocalDateTime dtmovimentacao) {
        this.dtmovimentacao = dtmovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }
}
