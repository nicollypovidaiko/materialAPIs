package com.example.estoque.controllers;

import com.example.estoque.entities.TipoMovimentacao;
import com.example.estoque.repositories.TipoMovimentacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipoMovimentacaoController {

    private final TipoMovimentacaoRepository tipoMovimentacaoRepository;

    public TipoMovimentacaoController(TipoMovimentacaoRepository tipoMovimentacaoRepository) {
        this.tipoMovimentacaoRepository = tipoMovimentacaoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoMovimentacao create(@RequestBody @Valid TipoMovimentacao tipoMovimentacao) {
        return tipoMovimentacaoRepository.save(tipoMovimentacao);
    }

    @GetMapping
    public List<TipoMovimentacao> findAll() {
        return tipoMovimentacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public TipoMovimentacao findById(@PathVariable long id) {
        return tipoMovimentacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
    }

    @PutMapping("/{id}")
    public TipoMovimentacao update(@PathVariable long id, @RequestBody @Valid TipoMovimentacao tipoMovimentacao) {
        TipoMovimentacao tipo = tipoMovimentacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tipo não encontrado"));
        tipo.setDescricao(tipoMovimentacao.getDescricao());
        return tipoMovimentacaoRepository.save(tipoMovimentacao);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        tipoMovimentacaoRepository.deleteById(id);
    }
}
