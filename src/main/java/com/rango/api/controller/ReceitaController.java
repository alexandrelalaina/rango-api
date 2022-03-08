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
import java.util.List;

@RestController
@RequestMapping("/receita")
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

        return assembler.toModel(receita);
    }

    @PostMapping
    public ReceitaResponseDTO add(@RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = disassembler.toDomainObject(receitaRequestDTO);

        receita = service.add(receita);

        return assembler.toModel(receita);
    }

    @PutMapping("/{id}")
    public ReceitaResponseDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = service.getById(id);

        disassembler.copyToDomainObject(receitaRequestDTO, receita);

        receita = service.add(receita);

        return assembler.toModel(receita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
