package com.rango.api.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceitaResponseDTO {

    private Integer id;
    private String descr;
    private BigDecimal vlMedio;
}
