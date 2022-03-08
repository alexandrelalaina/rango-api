package com.rango.api.assembler;

import com.rango.api.dto.response.ReceitaResponseDTO;
import com.rango.domain.model.Receita;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceitaResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ReceitaResponseDTO toModel(Receita receita) {
        return modelMapper.map(receita, ReceitaResponseDTO.class);
    }

    public List<ReceitaResponseDTO> toCollectionModel(List<Receita> receitas) {
        return receitas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
