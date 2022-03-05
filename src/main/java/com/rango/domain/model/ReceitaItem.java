package com.rango.domain.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "receita_item")
public class ReceitaItem {

    @EmbeddedId
    private ReceitaItemPK id;

}
