package com.rango.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

//    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_item", sequenceName = "seq_item")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_item")
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "possui_estoque", nullable = false)
    private Integer possuiEstoque;

    @Column(name = "imagem", nullable = true)
    private String imagem;

    @Column(name = "obs", nullable = true)
    private String obs;

}
