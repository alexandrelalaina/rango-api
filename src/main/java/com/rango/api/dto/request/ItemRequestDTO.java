package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemRequestDTO {

    @NotBlank
    @JsonProperty("descricao")
    private String descr;

    @NotNull
    private Integer possuiEstoque;

}
