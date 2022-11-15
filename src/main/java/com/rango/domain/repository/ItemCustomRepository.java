package com.rango.domain.repository;

import com.rango.domain.model.Item;
import com.rango.domain.repository.utils.MapCampoFilter;
import com.rango.domain.repository.utils.MapCampoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemCustomRepository {

    private final EntityManager em;

    ItemCustomRepository(EntityManager em){
        this.em = em;
    }

    public List<Item> filterCustomizado(
            Integer id,
            String descricao,
            Integer possuiEstoqueDe,
            Integer possuiEstoqueAte){

        List<MapCampoFilter> campoList = new ArrayList<>();

        if (id != null) {
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("id", id.toString(), "Integer"));
        }
        if (descricao != null){
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("descricao", descricao, "String", "descricao", "like"));
        }
        if (possuiEstoqueDe != null) {
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("possuiEstoque", possuiEstoqueDe.toString(), "Integer",  "possuiEstoqueDe", ">="));
        }
        if (possuiEstoqueAte != null){
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("possuiEstoque", possuiEstoqueAte.toString(), "Integer","possuiEstoqueAte", "<="));
        }

        Query query = MapCampoUtil.queryCustomizada(
                this.em,
                "Item",
                "i",
                campoList
        );
        return query.getResultList();
    }


}
