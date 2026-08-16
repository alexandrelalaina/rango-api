package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Schema(name = "ReceitaRequest", description = "Dados para criação ou atualização de receita.")
public class ReceitaRequestDTO {

    @Schema(description = "Identificador da receita", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank
    @JsonProperty("descricao")
    @Schema(description = "Descrição da receita", example = "Salpicão")
    private String descricao;

    @NotNull
    @Schema(description = "Valor da receita", example = "15.00")
    private BigDecimal valor;

    @Schema(description = "URL ou caminho da imagem", example = "https://exemplo.com/imagens/salpicao.png")
    private String imagem;

    @Schema(description = "Observações livres", example = "Servir gelado")
    private String obs;

}
