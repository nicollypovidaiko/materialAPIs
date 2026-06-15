package com.example.estoque.controllers;

import com.example.estoque.entities.Categoria;
import com.example.estoque.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@RequestBody @Valid Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @GetMapping
    public List<Categoria> findAll() {
        return  categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria findById(@PathVariable Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable long id, @RequestBody @Valid Categoria categoria) {
        categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
        categoria.setNome_cat(categoria.getNome_cat());
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        categoriaRepository.deleteById(id);
    }


}
