package com.rango.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {

//    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_item", sequenceName = "seq_item")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_item")
    private Integer id;

    @Column(name = "descr", nullable = false, length = 100)
    private String descr;

    @Column(name = "possui_estoque", nullable = false)
    private Integer possuiEstoque;

}
