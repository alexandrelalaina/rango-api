package com.rango.controller;


import com.rango.model.ReceitaItem;
import com.rango.service.ReceitaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
