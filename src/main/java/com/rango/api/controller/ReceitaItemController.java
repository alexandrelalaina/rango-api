package com.rango.api.controller;


import com.rango.api.assembler.ItemResponseAssembler;
import com.rango.api.assembler.ReceitaItemResponseAssembler;
import com.rango.api.dto.request.ReceitaItemRequestDTO;
import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.api.dto.response.ReceitaItemResponseDTO;
import com.rango.domain.model.Item;
import com.rango.domain.model.Receita;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.model.ReceitaItemPK;
import com.rango.domain.service.ItemService;
import com.rango.domain.service.ReceitaItemService;
import com.rango.domain.service.ReceitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/receitas-itens")
@Tag(name = "Receitas Itens", description = "Operações para associação entre receitas e itens.")
public class ReceitaItemController {

    @Autowired
    ReceitaItemService receitaItemService;

    @Autowired
    ReceitaService receitaService;

    @Autowired
    ItemService itemService;

    @Autowired
    private ReceitaItemResponseAssembler assembler;

    @Autowired
    private ItemResponseAssembler itemAssembler;

    @GetMapping
    @Operation(summary = "Lista todas as associações entre receitas e itens")
    public List<ReceitaItemResponseDTO> findAll(){
        List<ReceitaItem> receitaItems = receitaItemService.findAll();
        return assembler.toCollectionModel(receitaItems);
    }

    @GetMapping("/{receitaId}/itens")
    @Operation(summary = "Lista os itens de uma receita")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de itens retornada"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada")
    })
    public List<ItemResponseDTO> getItemList(@Parameter(description = "ID da receita") @PathVariable("receitaId") Integer receitaId){
        List<Item> itemList = receitaItemService.getItemListByReceitaId(receitaId);

        List<ItemResponseDTO> itemResponseDTOList = itemAssembler.toCollectionModel(itemList);
        return itemResponseDTOList;
    }

    @PostMapping
    @Operation(summary = "Vincula um item a uma receita")
    public ReceitaItemResponseDTO add(@RequestBody @Valid ReceitaItemRequestDTO request){
        Receita receita = receitaService.findById(request.getReceitaId());

        Item item = itemService.findById(request.getItemId());

        ReceitaItemPK receitaItemPK = ReceitaItemPK.builder()
                .receitaId(receita)
                .itemId(item)
                .build();

        ReceitaItem receitaItem = ReceitaItem.builder()
                .id(receitaItemPK)
                .build();

        receitaItem = receitaItemService.add(receitaItem);

        return assembler.toDTO(receitaItem);
    }

}
