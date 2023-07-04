package com.jhonny.medicationApi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_medicamento")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class Medicamento {

    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String principio_ativo;
    @NotNull
    private String descricao;
    @NotNull
    private String marca;
    @NotNull
    private String fabricante;
    @NotNull
    private double preco;
    @Column(columnDefinition = "double default 0.00")
    private double preco_desconto;
    @NotNull
    private boolean termolabel;

}
