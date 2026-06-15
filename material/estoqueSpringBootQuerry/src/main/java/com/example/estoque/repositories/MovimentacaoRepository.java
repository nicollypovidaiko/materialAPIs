package com.example.estoque.repositories;

import com.example.estoque.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("""
    SELECT m
    FROM Movimentacao m
    WHERE m.tipoMovimentacao.descricao = :descricao
    ORDER BY m.dtmovimentacao DESC
""")
    List<Movimentacao> listarSaidas(@Param("descricao") String descricao);

    @Query(value = """

            SELECT
        m.nome,
        m.unidade_medida,
    
        SUM(
            CASE
                WHEN tm.descricao = 'Entrada'
                THEN mov.quantidade
                ELSE 0
            END
        ) AS total_entradas,
    
        SUM(
            CASE
                WHEN tm.descricao = 'Saida'
                THEN mov.quantidade
                ELSE 0
            END
        ) AS total_saidas,
    
        SUM(
            CASE
                WHEN tm.descricao = 'Entrada'
                THEN mov.quantidade
                ELSE -mov.quantidade
            END
        ) AS saldo_periodo,
    
        SUM(
            CASE
                WHEN tm.descricao = 'Entrada'
                THEN mov.quantidade * m.valor_unit
                ELSE 0
            END
        ) AS valor_entradas,
    
        SUM(
            CASE
                WHEN tm.descricao = 'Saida'
                THEN mov.quantidade * m.valor_unit
                ELSE 0
            END
        ) AS valor_saidas
    
    FROM movimentacao mov
    INNER JOIN material m
        ON m.idmaterial = mov.idmaterial
    INNER JOIN tipo_movimentacao tm
        ON tm.idtipo = mov.idtipo
    
    WHERE mov.dt_movimentacao
          BETWEEN :inicio AND :fim
    
    GROUP BY
        m.idmaterial,
        m.nome,
        m.unidade_medida;
    """, nativeQuery = true)
    List<Object[]> listarPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);

    @Query(value = """
    SELECT
        m.nome,
        SUM(mov.quantidade) AS quantidade_saida,
        SUM(mov.quantidade * m.valor_unit) AS valor_total_saida
    FROM movimentacao mov
    INNER JOIN material m
        ON m.idmaterial = mov.idmaterial
    INNER JOIN tipo_movimentacao tm
        ON tm.idtipo = mov.idtipo
    WHERE tm.descricao = 'Saida'
      AND mov.dt_movimentacao BETWEEN :inicio AND :fim
    GROUP BY m.idmaterial, m.nome
    ORDER BY quantidade_saida DESC
    """, nativeQuery = true)
    List<Object[]> maiorSaidaPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim);
}
