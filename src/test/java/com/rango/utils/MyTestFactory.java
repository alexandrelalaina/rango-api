package com.rango.utils;

import com.rango.domain.model.Item;
import com.rango.domain.model.Receita;

import java.math.BigDecimal;

public class MyTestFactory {

    public static Item getItem1FrangoDesfiado(){
        return Item.builder()
                .id(1)
                .descricao("Frango Desfiado")
                .possuiEstoque(1)
                .build();
    }

    public static Item getItem2Cebola(){
        return Item.builder()
                .id(2)
                .descricao("Cebola")
                .possuiEstoque(1)
                .build();
    }

    public static Item getItem3Calabresa(){
        return Item.builder()
                .id(3)
                .descricao("Calabresa")
                .possuiEstoque(1)
                .build();
    }

    public static Receita getReceita1Salpicao() {
        return Receita.builder()
                .id(1)
                .descricao("Salpicão de Frango")
                .valor(new BigDecimal("20"))
                .build();
    }

    public static Receita getReceita2CalabresaAcebolada() {
        return Receita.builder()
                .id(2)
                .descricao("Calabresa Acebolada")
                .valor(new BigDecimal("10"))
                .build();
    }
}
