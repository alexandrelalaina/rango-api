package com.rango.service;

import com.rango.model.ReceitaItem;
import com.rango.repository.ReceitaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaItemService {

    @Autowired
    ReceitaItemRepository repository;

    public List<ReceitaItem> findAll(){
        return repository.findAll();
    }

}
