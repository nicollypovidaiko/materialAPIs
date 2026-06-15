package com.example.estoque.repositories;

import com.example.estoque.entities.Material;
import com.example.estoque.entities.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = """

            SELECT
                               nome,
                               quantidade,
                           
                               ROUND((quantidade * 100.0) / 100, 2) AS percentual,
                           
                               CASE
                                   WHEN quantidade <= 0 THEN 'MINIMO'
                                   WHEN quantidade >= 100 THEN 'MAXIMO'
                                   ELSE 'NORMAL'
                               END AS status
                           FROM material;
    """, nativeQuery = true)
    List<Object[]> verificarLimites();
}
