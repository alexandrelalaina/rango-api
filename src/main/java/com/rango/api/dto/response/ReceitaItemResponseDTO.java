package com.rango.api.dto.response;

import lombok.Data;

@Data
public class ReceitaItemResponseDTO {

    private ReceitaResponseDTO receita;

    private ItemResponseDTO item;
}
