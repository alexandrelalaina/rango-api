package com.rango.api.assembler;

import com.rango.api.dto.request.ReceitaRequestDTO;
import com.rango.domain.model.Receita;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceitaRequestDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Receita toDomainObject(ReceitaRequestDTO receitaRequestDTO) {
        return modelMapper.map(receitaRequestDTO, Receita.class);
    }

    public void copyToDomainObject(ReceitaRequestDTO receitaRequestDTO, Receita receita) {
        modelMapper.map(receitaRequestDTO, receita);
    }
}
