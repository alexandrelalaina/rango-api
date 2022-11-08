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
public class ReceitaResponseDTO {

    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private String imagem;
    private String obs;
}
