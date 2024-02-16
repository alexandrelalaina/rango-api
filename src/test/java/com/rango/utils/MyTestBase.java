package com.rango.utils;

import com.rango.domain.model.Item;
import com.rango.domain.model.Receita;
import com.rango.domain.model.ReceitaItem;
import com.rango.domain.model.ReceitaItemPK;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MyTestBase {

    protected Item item1Frango;
    protected Item item2Cebola;
    protected Item item3Calabresa;
    protected List<Item> itemList = new ArrayList<>();
    protected Receita receita1Salpicao;
    protected Receita receita2CalabresaAcebolada;
    protected List<Receita> receitaList = new ArrayList<>();
    protected ReceitaItem receita1SalpicaoItem1Frango;
    protected ReceitaItem receita2CalabresaAceboladaItem2Cebola;
    protected ReceitaItem receita2CalabresaAceboladaItem3Calabresa;
    protected List<ReceitaItem> receitaItemList = new ArrayList<>();

    public MyTestBase(){
        System.out.println("MyTestBase criacao");
        itens();
        receitas();
        receitasItens();
    }

    private void itens() {
        item1Frango = MyTestFactory.getItem1FrangoDesfiado();
        item2Cebola = MyTestFactory.getItem2Cebola();
        item3Calabresa = MyTestFactory.getItem3Calabresa();

        itemList.add(item1Frango);
        itemList.add(item2Cebola);
        itemList.add(item3Calabresa);
    }

    private void receitas() {
        receita1Salpicao = MyTestFactory.getReceita1Salpicao();
        receita2CalabresaAcebolada = MyTestFactory.getReceita2CalabresaAcebolada();

        receitaList.add(receita1Salpicao);
        receitaList.add(receita2CalabresaAcebolada);
    }

    private void receitasItens() {
        receita1SalpicaoItem1Frango = ReceitaItem.builder()
                .id(ReceitaItemPK.builder()
                        .receitaId(receita1Salpicao)
                        .itemId(item1Frango)
                        .build())
                .build();
        receita2CalabresaAceboladaItem2Cebola = ReceitaItem.builder()
                .id(ReceitaItemPK.builder()
                        .receitaId(receita2CalabresaAcebolada)
                        .itemId(item2Cebola)
                        .build())
                .build();
        receita2CalabresaAceboladaItem3Calabresa = ReceitaItem.builder()
                .id(ReceitaItemPK.builder()
                        .receitaId(receita2CalabresaAcebolada)
                        .itemId(item3Calabresa)
                        .build())
                .build();
    }

}
