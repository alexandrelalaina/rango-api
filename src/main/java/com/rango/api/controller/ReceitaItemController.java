package com.rango.api.controller;


import com.rango.domain.model.ReceitaItem;
import com.rango.domain.service.ReceitaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receita-item")
public class ReceitaItemController {

    @Autowired
    ReceitaItemService service;

    @GetMapping
    public ResponseEntity<List<ReceitaItem>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
