package com.rango.domain.exception;

// Trata qualquer erro de negócio que seja mais genérico
public class NegocioException extends RuntimeException {

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
