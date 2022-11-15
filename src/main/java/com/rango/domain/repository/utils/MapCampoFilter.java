package com.rango.domain.repository.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

public class MapCampoFilter {

    private String columnName; //nome do atributo da entity para utilizar no where
    private String queryParam;
    private String valor;
    private String condicao = "=";
    private String tipo = "String";

    public MapCampoFilter(String columnName, String valor){
        this.columnName = columnName;
        this.valor = valor;
    }

    public MapCampoFilter(String columnName, String valor, String tipo){
        this.columnName = columnName;
        this.valor = valor;
        this.tipo = tipo;
    }

    public MapCampoFilter(String columnName, String valor, String tipo, String queryParam, String condicao){
        this.columnName = columnName;
        this.valor = valor;
        this.tipo = tipo;
        this.queryParam = queryParam;
        this.condicao = condicao;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getQueryParam() {

        return queryParam != null ? this.queryParam : this.columnName;
    }

    public String getCondicao() {
        return condicao;
    }

    public String getTipo() {
        return tipo;
    }

    public Object getValor(){
        if (this.tipo.equals("BigDecimal")){
            if (this.valor != null){
                return new BigDecimal(this.valor);
            }
        } else if (this.tipo.equals("Integer")){
            if (this.valor != null){
                return Integer.parseInt(this.valor);
            }
        }
        return valor;
    }

}
