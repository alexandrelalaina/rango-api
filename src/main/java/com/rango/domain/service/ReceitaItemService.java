package com.rango.domain.service;

import com.rango.domain.model.Item;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.repository.ItemRepository;
import com.rango.domain.repository.ReceitaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaItemService {

    @Autowired
    ReceitaItemRepository repository;

    @Autowired
    ItemRepository itemRepository;

    public List<ReceitaItem> findAll(){
        return repository.findAll();
    }

    public ReceitaItem add(ReceitaItem receitaItem){
        return repository.save(receitaItem);
    }

    public List<Item> getByReceitaId(Integer receitaId) {
//        List<Item> itemList = repository.getItemListByReceita(receitaId);
        List<Item> itemList = itemRepository.getItemListByReceitaId(receitaId);
        return itemList;
    }

}
