package com.rango.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_receita")
    private Integer id;

    @Column(name = "descr", nullable = false, length = 100)
    private String descr;

    @Column(name = "vl_medio", nullable = false)
    private BigDecimal vlMedio;

}
