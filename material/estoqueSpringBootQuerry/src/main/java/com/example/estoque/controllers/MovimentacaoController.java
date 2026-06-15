package com.example.estoque.controllers;


import com.example.estoque.DTO.EntradaDTO;
import com.example.estoque.entities.Material;
import com.example.estoque.entities.Movimentacao;
import com.example.estoque.entities.TipoMovimentacao;
import com.example.estoque.repositories.MaterialRepository;
import com.example.estoque.repositories.MovimentacaoRepository;
import com.example.estoque.repositories.TipoMovimentacaoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final TipoMovimentacaoRepository tipoMovimentacaoRepository;
    private final MaterialRepository materialRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoController(TipoMovimentacaoController tipoMovimentacaoController, TipoMovimentacaoRepository tipoMovimentacaoRepository, MaterialRepository materialRepository, MovimentacaoRepository movimentacaoRepository) {
        this.tipoMovimentacaoRepository = tipoMovimentacaoRepository;
        this.materialRepository = materialRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @PostMapping
    public Movimentacao save(@RequestBody @Valid Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    @GetMapping
    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movimentacao findById(@PathVariable Long id) {
        return movimentacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimentacao não encontrado"));
    }

    @PutMapping("/{id}")
    public  Movimentacao update(@PathVariable Long id, @RequestBody @Valid Movimentacao movimentacao) {
        movimentacao = movimentacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimentacao não encontrado"));
        movimentacao.setDtmovimentacao(movimentacao.getDtmovimentacao());
        movimentacao.setQuantidade(movimentacao.getQuantidade());
        movimentacao.setMaterial(movimentacao.getMaterial());
        movimentacao.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
        return movimentacaoRepository.save(movimentacao);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        movimentacaoRepository.deleteById(id);
    }

    //4. Listar todas as saídas de materiais
    //O sistema deverá listar todas as saídas de materiais, apresentadas em ordem decrescente por data.
    @GetMapping("/saidas")
    public List<Movimentacao> getSaidas() {
        return movimentacaoRepository.listarSaidas("saida");
    }

    //5. Registrar entradas de materiais no estoque
    //O sistema deverá permitir o registro de entradas de materiais no estoque, atualizando a quantidade disponível.
    @PostMapping("/entrada")
    public Movimentacao entrada(@RequestBody @Valid EntradaDTO dto) {
        Material m = materialRepository.findById(dto.getIdmaterial()).orElseThrow();

        TipoMovimentacao entrada = tipoMovimentacaoRepository.findByDescricao("Entrada");


        m.setQuantidade(m.getQuantidade() + dto.getQuantidade());

        materialRepository.save(m);

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setDtmovimentacao(LocalDateTime.now());
        movimentacao.setQuantidade(dto.getQuantidade());
        movimentacao.setMaterial(m);
        movimentacao.setTipoMovimentacao(entrada);

        return movimentacaoRepository.save(movimentacao);


    }

    //6. Listar movimentações de entrada e saída por período
    //O sistema deverá listar as movimentações de entrada e saída no período informado, conforme data inicial e data final.
    //
    //A consulta deverá apresentar os 7 campos obrigatórios, na seguinte ordem:
    //
    //nome do material;
    //unidade de medida;
    //total de entradas;
    //total de saídas;
    //saldo no período;
    //valor total financeiro das entradas;
    //valor total financeiro das saídas.
    @GetMapping("/periodo")
    public List<Object[]> listarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim){

        return movimentacaoRepository
                .listarPorPeriodo(inicio, fim);
    }

    //7. Listar os materiais com maior volume de saída no período
    //O sistema deverá listar os materiais com maior volume de saída no período informado, conforme data inicial e data final.
    //
    //
    //A consulta deverá apresentar os 3 campos obrigatórios, na seguinte ordem:
    //nome do material;
    //quantidade total de saída;
    //valor total financeiro das saídas.
    @GetMapping("/maior-saida")
    public List<Object[]> maiorSaida(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim){

        return movimentacaoRepository
                .maiorSaidaPeriodo(inicio, fim);
    }
}
