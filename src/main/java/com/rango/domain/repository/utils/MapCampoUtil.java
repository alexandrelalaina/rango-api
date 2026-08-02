package com.rango.domain.repository.utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Locale;

public class MapCampoUtil {

    public static void addToMap(List<MapCampoFilter> mapCamposList,
                                MapCampoFilter mapCampos){
        if (mapCampos.getValor() != null){
            mapCamposList.add(mapCampos);
        }
    }

    public static Query queryCustomizada(EntityManager em,
                                         String entityName,
                                         String entityAlias,
                                         List<MapCampoFilter> campoList){
        return queryCustomizada(em, entityName, entityAlias, campoList, null);
    }

    public static Query queryCustomizada(EntityManager em,
                                         String entityName,
                                         String entityAlias,
                                         List<MapCampoFilter> campoList,
                                         String orderBy){
        StringBuilder sb = new StringBuilder("select " + entityAlias + " from " + entityName + " as " + entityAlias);
        String condicao = " where ";

        if (! campoList.isEmpty()) {
            for (MapCampoFilter registro : campoList) {
                if (registro.getCondicao().equals("like")){
                    sb.append(condicao + "LOWER(" + entityAlias + "." + registro.getColumnName() + ") " + registro.getCondicao() + " CONCAT('%', :" + registro.getQueryParam() + ", '%')");
                } else {
                    sb.append(condicao + entityAlias + "." + registro.getColumnName() + " " + registro.getCondicao() + " :" + registro.getQueryParam());
                }
                condicao = " and ";
            }

            if (orderBy != null && !orderBy.isBlank()) {
                sb.append(" order by ").append(orderBy);
            }

            System.out.println(sb.toString());
            Query query = em.createQuery(sb.toString());

            for (MapCampoFilter registro : campoList) {
                if (registro.getCondicao().equals("like")) {
                    query.setParameter(registro.getQueryParam(), registro.getValor().toString().toLowerCase());
                } else {
                    query.setParameter(registro.getQueryParam(), registro.getValor());
                }
            }

            return query;

        }

        if (orderBy != null && !orderBy.isBlank()) {
            sb.append(" order by ").append(orderBy);
        }

        Query query = em.createQuery(sb.toString());
        return query;

    }
}
