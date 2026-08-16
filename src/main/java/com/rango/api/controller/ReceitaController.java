package com.rango.api.controller;

import com.rango.api.assembler.ReceitaRequestDisassembler;
import com.rango.api.assembler.ReceitaResponseAssembler;
import com.rango.api.dto.request.ReceitaRequestDTO;
import com.rango.api.dto.response.ReceitaResponseDTO;
import com.rango.domain.model.Receita;
import com.rango.domain.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
@Tag(name = "Receitas", description = "Operações para gerenciamento de receitas.")
public class ReceitaController {

    @Autowired
    ReceitaService service;

    @Autowired
    private ReceitaResponseAssembler assembler;

    @Autowired
    private ReceitaRequestDisassembler disassembler;

    @GetMapping
    @Operation(summary = "Lista todas as receitas")
    public List<ReceitaResponseDTO> findAll(){
        List<Receita> receitas = service.findAll();

        return assembler.toCollectionModel(receitas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma receita por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receita encontrada"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada")
    })
    public ReceitaResponseDTO getById(@Parameter(description = "ID da receita") @PathVariable("id") Integer id){
        Receita receita = service.findById(id);

        return assembler.toDTO(receita);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filtra receitas por critérios opcionais")
    public List<ReceitaResponseDTO> filter(
            @Parameter(description = "ID da receita") @RequestParam(value = "id", required = false ) Integer id ,
            @Parameter(description = "Descrição parcial") @RequestParam(value = "descricao", required = false) String descricao,
            @Parameter(description = "Valor mínimo") @RequestParam(value = "valorDe", required = false) BigDecimal valorDe,
            @Parameter(description = "Valor máximo") @RequestParam(value = "valorAte", required = false) BigDecimal valorAte)
    {
        return assembler.toCollectionModel(service.filter(id, descricao, valorDe, valorAte));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova receita")
    public ReceitaResponseDTO add(@RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = disassembler.toDomainObject(receitaRequestDTO);

        receita = service.add(receita);

        return assembler.toDTO(receita);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma receita")
    public ReceitaResponseDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ReceitaRequestDTO receitaRequestDTO){
        Receita receita = service.findById(id);

        disassembler.copyToDomainObject(receitaRequestDTO, receita);

        receita = service.add(receita);

        return assembler.toDTO(receita);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove uma receita")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
