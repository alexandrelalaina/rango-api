package com.rango.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private Integer id;
    private String descr;
    private Integer possuiEstoque;
}
