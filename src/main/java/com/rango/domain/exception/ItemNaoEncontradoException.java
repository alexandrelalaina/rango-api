package com.rango.domain.exception;

public class ItemNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ItemNaoEncontradoException(Integer id) {
        this("Item não encontrado com o ID " + id);
    }

}
