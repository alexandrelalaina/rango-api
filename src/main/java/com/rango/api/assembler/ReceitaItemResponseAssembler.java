package com.rango.api.assembler;

import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.api.dto.response.ReceitaItemResponseDTO;
import com.rango.api.dto.response.ReceitaResponseDTO;
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

//    public ReceitaItemResponseDTO toDTO(ReceitaItem receitaItem){
//        return modelMapper.map(receitaItem, ReceitaItemResponseDTO.class);
//    }

    public ReceitaItemResponseDTO toDTO(ReceitaItem receitaItem){
        return ReceitaItemResponseDTO.builder()
                .receita(ReceitaResponseDTO.builder()
                        .id(receitaItem.getId().getReceitaId().getId())
                        .descr(receitaItem.getId().getReceitaId().getDescr())
                        .vlMedio(receitaItem.getId().getReceitaId().getVlMedio())
                        .build())
                .item(ItemResponseDTO.builder()
                        .id(receitaItem.getId().getItemId().getId())
                        .descr(receitaItem.getId().getItemId().getDescr())
                        .possuiEstoque(receitaItem.getId().getItemId().getPossuiEstoque())
                        .build())
                .build();
//                return modelMapper.map(receitaItem, ReceitaItemResponseDTO.class);
    }

    public List<ReceitaItemResponseDTO> toCollectionModel(List<ReceitaItem> receitaItens){
        return receitaItens.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }



}
