package com.rango.domain.service;

import com.rango.domain.model.Item;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.repository.ItemRepository;
import com.rango.domain.repository.ReceitaItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaItemService {

    private final ReceitaItemRepository repository;
    private final ItemRepository itemRepository;

    public ReceitaItemService(ReceitaItemRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public List<ReceitaItem> findAll(){
        return repository.findAll();
    }

    public ReceitaItem add(ReceitaItem receitaItem){
        return repository.save(receitaItem);
    }

    public List<Item> getItemListByReceitaId(Integer receitaId) {
//        List<Item> itemList = repository.getItemListByReceita(receitaId);
        List<Item> itemList = itemRepository.getItemListByReceitaId(receitaId);
        return itemList;
    }

}
