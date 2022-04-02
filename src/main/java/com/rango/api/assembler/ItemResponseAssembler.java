package com.rango.api.assembler;

import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.domain.model.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ItemResponseDTO toDTO(Item item) {
        return modelMapper.map(item, ItemResponseDTO.class);
    }

    public List<ItemResponseDTO> toCollectionModel(List<Item> itens) {
        return itens.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
