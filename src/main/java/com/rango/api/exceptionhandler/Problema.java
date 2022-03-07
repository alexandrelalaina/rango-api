package com.rango.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;

    private String titulo;

    private String detalhe;

    private String mensagemUsuario;

    private OffsetDateTime timestamp;

    private List<Objeto> objetos;

    @Getter
    @Builder
    public static class Objeto {

        private String nome;

        private String mensagemUsuario;

    }
}
