package com.example.estoque.DTO;

public class EntradaDTO {

    private Long idmaterial;
    private Long idtipo;
    private Integer quantidade;


    public Long getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Long idtipo) {
        this.idtipo = idtipo;
    }

    public Long getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Long idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

