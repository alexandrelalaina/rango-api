package com.rango.domain.service;

import com.rango.domain.exception.ItemNaoEncontradoException;
import com.rango.domain.model.Item;
import com.rango.domain.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException(id));
    }

    public Item add(Item item) {
        return repository.save(item);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNaoEncontradoException(id);
        }
    }
}