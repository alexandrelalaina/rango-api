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
@Table(name = "receita")
public class Receita {

    @Id
    @SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_receita")
    private Integer id;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "imagem", nullable = true)
    private String imagem;

    @Column(name = "obs", nullable = true)
    private String obs;
}
