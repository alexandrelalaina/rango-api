package com.rango.domain.repository;

import com.rango.domain.model.Receita;
import com.rango.domain.repository.utils.MapCampoFilter;
import com.rango.domain.repository.utils.MapCampoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceitaCustomRepository {

    private final EntityManager em;

    ReceitaCustomRepository(EntityManager em){
        this.em = em;
    };

    public List<Receita> filterCustomizado(Integer id,
                                           String descricao,
                                           BigDecimal valorDe,
                                           BigDecimal valorAte){

        List<MapCampoFilter> campoList = new ArrayList<>();

        if (id != null) {
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("id", id.toString(), "Integer"));
        }
        if (descricao != null) {
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("descricao", descricao, "String", "descricao", "like"));
        }
        if (valorDe != null) {
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("valor", valorDe.toString(), "BigDecimal",  "valorDe", ">="));
        }
        if (valorAte != null){
            MapCampoUtil.addToMap(campoList, new MapCampoFilter("valor", valorAte.toString(), "BigDecimal","valorAte", "<="));
        }

        Query query = MapCampoUtil.queryCustomizada(
                this.em,
                "Receita",
                "r",
                campoList);
        return query.getResultList();
    }

}
