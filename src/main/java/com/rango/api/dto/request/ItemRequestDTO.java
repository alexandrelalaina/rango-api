package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemRequestDTO {

    @NotBlank
    @JsonProperty("descricao")
    private String descricao;

    @NotNull
    private Integer possuiEstoque;

    private String imagem;

    private String obs;
}
