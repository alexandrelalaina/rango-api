package com.rango.domain.exception;

public class ReceitaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public ReceitaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public ReceitaNaoEncontradaException(Integer id) {
        this("Receita não encontrada com o ID " + id);
    }

}
