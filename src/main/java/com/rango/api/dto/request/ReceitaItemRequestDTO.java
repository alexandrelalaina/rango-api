package com.rango.api.dto.request;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "ReceitaItemRequest", description = "Associação entre uma receita e um item.")
public class ReceitaItemRequestDTO {

    @Schema(description = "Identificador da receita", example = "1")
    private Integer receitaId;

    @Schema(description = "Identificador do item", example = "2")
    private Integer itemId;
}
