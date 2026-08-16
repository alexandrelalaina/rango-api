package com.rango.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ReceitaItemResponse", description = "Representação da relação entre receita e item.")
public class ReceitaItemResponseDTO {

    @Schema(description = "Receita vinculada")
    private ReceitaResponseDTO receita;

    @Schema(description = "Item vinculado")
    private ItemResponseDTO item;
}
