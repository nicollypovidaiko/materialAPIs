package com.example.estoque.repositories;

import com.example.estoque.entities.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimentacaoRepository extends JpaRepository<TipoMovimentacao, Long> {
    TipoMovimentacao findByDescricao(String entrada);
}
