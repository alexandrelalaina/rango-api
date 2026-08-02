package com.rango.api.assembler;

import com.rango.api.dto.response.ItemResponseDTO;
import com.rango.api.dto.response.ReceitaItemResponseDTO;
import com.rango.api.dto.response.ReceitaResponseDTO;
import com.rango.domain.model.ReceitaItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReceitaItemResponseAssembler {

    public ReceitaItemResponseDTO toDTO(ReceitaItem receitaItem){
        return ReceitaItemResponseDTO.builder()
                .receita(ReceitaResponseDTO.builder()
                        .id(receitaItem.getId().getReceitaId().getId())
                        .descricao(receitaItem.getId().getReceitaId().getDescricao())
                        .valor(receitaItem.getId().getReceitaId().getValor())
                        .imagem(receitaItem.getId().getReceitaId().getImagem())
                        .build())
                .item(ItemResponseDTO.builder()
                        .id(receitaItem.getId().getItemId().getId())
                        .descricao(receitaItem.getId().getItemId().getDescricao())
                        .imagem(receitaItem.getId().getItemId().getImagem())
                        .possuiEstoque(receitaItem.getId().getItemId().getPossuiEstoque())
                        .obs(receitaItem.getId().getItemId().getObs())
                        .build())
                .build();
    }

    public List<ReceitaItemResponseDTO> toCollectionModel(List<ReceitaItem> receitaItens){
        Map<Integer, List<ReceitaItem>> receitasAgrupadas = receitaItens.stream()
                .collect(Collectors.groupingBy(ri -> ri.getId().getReceitaId().getId()));

        return receitaItens.stream()
                .map(receitaItem -> {
                    ReceitaItemResponseDTO dto = toDTO(receitaItem);
                    
                    boolean possuiEstoque = receitasAgrupadas
                            .get(receitaItem.getId().getReceitaId().getId())
                            .stream()
                            .allMatch(ri -> ri.getId().getItemId().getPossuiEstoque() > 0);
                    
                    dto.getReceita().setPossuiEstoque( possuiEstoque == true ? 1 : 0 );
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
