package com.rango.controller;

import com.rango.model.Receita;
import com.rango.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    ReceitaService service;

    @GetMapping
    public ResponseEntity<List<Receita>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getById(@PathVariable("id") Integer id){
        Receita receita = service.getById(id);
        if (receita!=null){
            return ResponseEntity.ok(receita);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Receita> add(@RequestBody Receita receita){
        receita.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(receita));
    }

    @PutMapping
    public  ResponseEntity<Receita> update(@RequestBody Receita receita){
        return ResponseEntity.ok(service.update(receita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Receita> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
