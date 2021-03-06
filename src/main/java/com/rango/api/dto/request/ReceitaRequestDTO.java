package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ReceitaRequestDTO {

    @NotBlank
    @JsonProperty("descricao")
    private String descr;

    @NotNull
    private BigDecimal vlMedio;

}
