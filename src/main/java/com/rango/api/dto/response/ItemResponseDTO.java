package com.rango.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private Integer id;
    private String descricao;
    private String imagem;
    private Integer possuiEstoque;
    private Float quantidade;
    private BigDecimal valor;
    private String obs;
}
