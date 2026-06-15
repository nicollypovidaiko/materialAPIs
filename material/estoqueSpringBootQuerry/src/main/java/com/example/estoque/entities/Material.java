package com.example.estoque.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idmaterial;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    @Positive
    private BigDecimal valor_unit;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private int quantidade;

    @NotNull
    private String unidadeMedida;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "idcategoria",nullable = false)
    private Categoria categoria;


    public Material() {
    }


    public Material(long idmaterial, String nome, String descricao, BigDecimal valor_unit, int quantidade, Categoria categoria) {
        this.idmaterial = idmaterial;
        this.nome = nome;
        this.descricao = descricao;
        this.valor_unit = valor_unit;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public Material(String nome, String descricao, BigDecimal valor_unit, int quantidade, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor_unit = valor_unit;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public long getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(long idmaterial) {
        this.idmaterial = idmaterial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(BigDecimal valor_unit) {
        this.valor_unit = valor_unit;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

