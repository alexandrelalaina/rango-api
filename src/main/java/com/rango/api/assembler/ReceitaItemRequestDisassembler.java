package com.rango.api.assembler;

import com.rango.api.dto.request.ReceitaItemRequestDTO;
import com.rango.api.dto.response.ReceitaItemResponseDTO;
import com.rango.domain.model.ReceitaItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceitaItemRequestDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public ReceitaItem toDomainObject(ReceitaItemRequestDTO receitaItemRequestDTO){
        return modelMapper.map(receitaItemRequestDTO, ReceitaItem.class);
    }

    public void copyToDomainObject(ReceitaItemRequestDTO receitaItemRequestDTO, ReceitaItem receitaItem){
        modelMapper.map(receitaItemRequestDTO, receitaItem);
    }
}
