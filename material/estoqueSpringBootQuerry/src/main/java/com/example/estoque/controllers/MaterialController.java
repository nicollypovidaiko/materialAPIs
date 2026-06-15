package com.example.estoque.controllers;

import com.example.estoque.DTO.EntradaDTO;
import com.example.estoque.entities.Material;
import com.example.estoque.entities.TipoMovimentacao;
import com.example.estoque.repositories.MaterialRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/material")
public class MaterialController {

    private final MaterialRepository materialRepository;

    public MaterialController(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    //3. Cadastrar novos materiais
    //O sistema deverá permitir o cadastro de novos materiais, contendo validações para os seguintes campos:
    //valor unitário;
    //quantidade;
    //categoria.
    @PostMapping
    public Material create(@RequestBody @Valid Material material) {
        return materialRepository.save(material);
    }

    //2. Listar todos os materiais cadastrados
    //O sistema deverá permitir a listagem completa de todos os materiais cadastrados no banco de dados.
    @GetMapping
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @GetMapping("/{id}")
    public Material findById(@PathVariable Long id) {
        return materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Material não encontrado"));
    }

    @PutMapping("/{id}")
    public Material update(@PathVariable Long id, @RequestBody @Valid Material material) {
        material = materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Material não encontrado"));
        material.setNome(material.getNome());
        material.setDescricao(material.getDescricao());
        material.setValor_unit(material.getValor_unit());
        material.setQuantidade(material.getQuantidade());
        material.setCategoria(material.getCategoria());
        return materialRepository.save(material);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        materialRepository.deleteById(id);
    }

    //1. Listar o valor total por categoria do material
    //O sistema deverá listar o valor total por categoria do material
    @GetMapping("/valor-cat")
    public Map<String,Double> getValorCat() {
        List<Material> materials = materialRepository.findAll();

        Map<String,Double> result = new HashMap<>();

        for (Material m : materials) {
            String cat = m.getCategoria().getNome_cat();
            BigDecimal valor = m.getValor_unit()
                    .multiply(BigDecimal.valueOf(m.getQuantidade()));

            result.put(cat, result.getOrDefault(cat,0.0)+valor.doubleValue());
        }
        return result;
    }

    //8. Identificar materiais que atingiram limites de estoque
    //O sistema deverá identificar os materiais que tenham atingido os limites estabelecidos para o estoque:
    //limite mínimo: 0;
    //limite máximo: 100.
    //Também deverá ser apresentado o percentual de nível atingido no estoque.
    @GetMapping("/limites")
    public List<Object[]> verificarLimites(){
        return materialRepository.verificarLimites();
    }




}
