package com.rango.service;

import com.rango.model.Item;
import com.rango.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }

    public Item getById(Integer id){
        return repository.getById(id);
    }

    public Item add(Item item){
        item.setId(null);
        return repository.save(item);
    }

    public Item update(Item item){
        return repository.save(item);
    }

    public void delete(Integer id){
        Item item = repository.getById(id);
        repository.delete(item);
    }
}
