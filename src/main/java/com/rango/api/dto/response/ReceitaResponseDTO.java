package com.rango.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ReceitaResponse", description = "Representação de uma receita retornada pela API.")
public class ReceitaResponseDTO {

    @Schema(example = "1")
    private Integer id;
    @Schema(example = "Salpicão")
    private String descricao;
    @Schema(example = "15.00")
    private BigDecimal valor;
    @Schema(example = "https://exemplo.com/imagens/salpicao.png")
    private String imagem;
    @Schema(example = "Servir gelado")
    private String obs;
    @Schema(example = "7")
    private Integer possuiEstoque;
}
