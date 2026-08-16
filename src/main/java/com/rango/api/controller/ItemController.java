package com.rango.api.controller;

import com.rango.api.assembler.ItemRequestDisassembler;
import com.rango.api.assembler.ItemResponseAssembler;
import com.rango.api.dto.request.ItemRequestDTO;
import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.domain.model.Item;
import com.rango.domain.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/itens") // por Default essa sera a URI atendida
@Tag(name = "Itens", description = "Operações para gerenciamento de itens.")
public class ItemController {

    @Autowired
    private ItemService service;

    @Autowired
    private ItemResponseAssembler assembler;

    @Autowired
    private ItemRequestDisassembler disassembler;

    @GetMapping
    @Operation(summary = "Lista todos os itens")
    public List<ItemResponseDTO> findAll() {
        List<Item> items = service.findAll();

        return assembler.toCollectionModel(items);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um item por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    public ItemResponseDTO getById(@Parameter(description = "ID do item") @PathVariable("id") Integer id) {
        Item item = service.findById(id);

        return assembler.toDTO(item);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filtra itens por critérios opcionais")
    public List<ItemResponseDTO> filter(
            @Parameter(description = "ID do item") @RequestParam(value = "id", required = false) Integer id,
            @Parameter(description = "Descrição parcial") @RequestParam(value = "descricao", required = false) String descricao,
            @Parameter(description = "Estoque mínimo") @RequestParam(value = "possuiEstoqueDe", required = false) Integer possuiEstoqueDe,
            @Parameter(description = "Estoque máximo") @RequestParam(value = "possuiEstoqueAte", required = false) Integer possuiEstoqueAte,
            @Parameter(description = "Filtro por consumo direto") @RequestParam(value = "consumoDireto", required = false) Boolean consumoDireto){
        return assembler.toCollectionModel(service.filter(id, descricao, possuiEstoqueDe, possuiEstoqueAte, consumoDireto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo item")
    public ItemResponseDTO add(@RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        Item item = disassembler.toDomainObject(itemRequestDTO);

        item = service.add(item);

        return assembler.toDTO(item);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um item por completo")
    public ItemResponseDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        return updateItem(id, itemRequestDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um item")
    public ItemResponseDTO patch(@PathVariable("id") Integer id, @RequestBody ItemRequestDTO itemRequestDTO) {
        return updateItem(id, itemRequestDTO);
    }

    private ItemResponseDTO updateItem(Integer id, ItemRequestDTO itemRequestDTO) {
        Item item = service.findById(id);

        disassembler.copyToDomainObject(itemRequestDTO, item);

        item = service.add(item);

        return assembler.toDTO(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove um item")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
