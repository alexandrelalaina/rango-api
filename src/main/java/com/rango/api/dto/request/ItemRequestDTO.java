package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "ItemRequest", description = "Dados para criação ou atualização de item.")
public class ItemRequestDTO {

    @Schema(description = "Identificador do item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank
    @JsonProperty("descricao")
    @Schema(description = "Descrição do item", example = "Frango desfiado")
    private String descricao;

    @NotNull
    @Schema(description = "Quantidade em estoque", example = "4")
    private Integer possuiEstoque;

    @Schema(description = "URL ou caminho da imagem", example = "https://exemplo.com/imagens/frango.png")
    private String imagem;

    @Schema(description = "Observações livres", example = "Sem sal")
    private String obs;

    @Schema(description = "Indica se o item é de consumo direto", example = "true")
    private Boolean consumoDireto;

    @Schema(description = "Indica se o item é favorito", example = "false")
    private Boolean favorito;

}
