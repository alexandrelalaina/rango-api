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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/receitas-itens")
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
    public List<ReceitaItemResponseDTO> findAll(){
        List<ReceitaItem> receitaItems = receitaItemService.findAll();
        return assembler.toCollectionModel(receitaItems);
    }

    @GetMapping("/{receitaId}/itens")
    public List<ItemResponseDTO> getItemList(@PathVariable("receitaId") Integer receitaId){
        List<Item> itemList = receitaItemService.getItemListByReceitaId(receitaId);

        List<ItemResponseDTO> itemResponseDTOList = itemAssembler.toCollectionModel(itemList);
        return itemResponseDTOList;
    }

    @PostMapping
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
