package com.rango.api.assembler;

import com.rango.api.dto.request.ItemRequestDTO;
import com.rango.domain.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemRequestDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Item toDomainObject(ItemRequestDTO itemRequestDTO) {
        return modelMapper.map(itemRequestDTO, Item.class);
    }

    public void copyToDomainObject(ItemRequestDTO itemRequestDTO, Item item) {
        modelMapper.map(itemRequestDTO, item);
    }
}
