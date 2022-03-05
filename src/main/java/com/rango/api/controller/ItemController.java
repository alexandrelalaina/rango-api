package com.rango.api.controller;

import com.rango.api.assembler.ItemRequestDisassembler;
import com.rango.api.assembler.ItemResponseAssembler;
import com.rango.api.dto.request.ItemRequestDTO;
import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.domain.exception.ItemNaoEncontradoException;
import com.rango.domain.exception.NegocioException;
import com.rango.domain.model.Item;
import com.rango.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item") // por Default essa sera a URI atendida
public class ItemController {

    @Autowired
    private ItemService service;

    @Autowired
    private ItemResponseAssembler assembler;

    @Autowired
    private ItemRequestDisassembler disassembler;

    @GetMapping
    public List<ItemResponseDTO> findAll() {
        List<Item> items = service.findAll();

        return assembler.toCollectionModel(items);
    }

    @GetMapping("/{id}")
    public ItemResponseDTO getById(@PathVariable("id") Integer id) {
        Item item = service.getById(id);

        return assembler.toModel(item);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponseDTO add(@RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        Item item = disassembler.toDomainObject(itemRequestDTO);

        item = service.add(item);

        return assembler.toModel(item);
    }

    @PutMapping("/{id}")
    public ItemResponseDTO update(@PathVariable("id") Integer id, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        Item item = service.getById(id);

        disassembler.copyToDomainObject(itemRequestDTO, item);

        item = service.add(item);

        return assembler.toModel(item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

}
