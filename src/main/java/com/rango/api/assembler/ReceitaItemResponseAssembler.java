package com.rango.api.assembler;

import com.rango.api.dto.response.ReceitaItemResponseDTO;
import com.rango.domain.model.ReceitaItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceitaItemResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ReceitaItemResponseDTO toDTO(ReceitaItem receitaItem){
        return modelMapper.map(receitaItem, ReceitaItemResponseDTO.class);
    }

    public List<ReceitaItemResponseDTO> toCollectionModel(List<ReceitaItem> receitaItens){
        return receitaItens.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



}
