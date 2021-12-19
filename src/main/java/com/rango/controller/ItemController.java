package com.rango.controller;

import com.rango.model.Item;
import com.rango.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item") // por Default essa sera a URI atendida
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<List<Item>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable("id") Integer id){
        Item item = service.getById(id);
        if (item!=null){
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Item> add(@RequestBody Item item){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.add(item));
    }

    @PutMapping
    public ResponseEntity<Item> update(@RequestBody Item item){
        return ResponseEntity.ok(service.update(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
