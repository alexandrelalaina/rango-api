package com.rango.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    // Use IDENTITY for H2 to avoid creating sequences (better compatibility across H2 versions)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
