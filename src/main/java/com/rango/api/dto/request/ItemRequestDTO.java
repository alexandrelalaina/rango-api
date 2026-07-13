package com.rango.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemRequestDTO {

    private Integer id;

    @NotBlank
    @JsonProperty("descricao")
    private String descricao;

    @NotNull
    private Integer possuiEstoque;

    private String imagem;

    private String obs;

    private Boolean consumoDireto;

}
