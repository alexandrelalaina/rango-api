package com.rango.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ItemResponse", description = "Representação de um item retornado pela API.")
public class ItemResponseDTO {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "Frango desfiado")
    private String descricao;
    @Schema(example = "4")
    private Integer possuiEstoque;
    @Schema(example = "https://exemplo.com/imagens/frango.png")
    private String imagem;
    @Schema(example = "Sem sal")
    private String obs;
    @Schema(example = "true")
    private Boolean consumoDireto;
    @Schema(example = "false")
    private Boolean favorito;
}
