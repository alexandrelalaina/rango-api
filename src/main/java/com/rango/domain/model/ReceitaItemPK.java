package com.rango.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Esta classe representa a composição da chave da Receita + Item.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReceitaItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "fk_receita_id", nullable = false)
    private Receita receitaId;

    @ManyToOne
    @JoinColumn(name = "fk_item_id", nullable = false)
    private Item itemId;

}
