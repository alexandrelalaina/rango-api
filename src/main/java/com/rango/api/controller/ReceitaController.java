package com.rango.api.controller;

import com.rango.api.assembler.ReceitaRequestDisassembler;
import com.rango.api.assembler.ReceitaResponseAssembler;
import com.rango.api.dto.request.ReceitaRequestDTO;
import com.rango.api.dto.response.ReceitaResponseDTO;
import com.rango.domain.model.Receita;
import com.rango.domain.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    ReceitaService service;

    @Autowired
    private ReceitaResponseAssembler assembler;

    @Autowired
    private ReceitaRequestDisassembler disassembler;

    @GetMapping
    public List<ReceitaResponseDTO> findAll(){
        List<Receita> receitas = service.findAll();

        return assembler.toCollectionModel(receitas);
    }

    @GetMapping("/{id}")
    public ReceitaResponseDTO getById(@PathVariable("id") Integer id){
        Receita receita = service.getById(id);

        return assembler.toDTO(receita);
    }

    @GetMapping("/filter")
    public List<ReceitaResponseDTO> filter(
            @RequestParam(value = "id", required = false ) Integer id ,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "valorDe", required = false) BigDecimal valorDe,
            @RequestParam(value = "valorAte", required = false) BigDecimal valorAte)
    {
        return assembler.toCollectionModel(service.filter(id, descricao, valorDe, valorAte));
    }

    @PostMapping
    public ReceitaResponseDTO add(@RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = disassembler.toDomainObject(receitaRequestDTO);

        receita = service.add(receita);

        return assembler.toDTO(receita);
    }

    @PutMapping("/{id}")
    public ReceitaResponseDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = service.getById(id);

        disassembler.copyToDomainObject(receitaRequestDTO, receita);

        receita = service.add(receita);

        return assembler.toDTO(receita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
