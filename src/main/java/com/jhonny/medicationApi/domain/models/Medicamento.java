package com.jhonny.medicationApi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_medicamento")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String principio_ativo;

    private String descricao;

    private String marca;

    private String fabricante;

    private double preco;

    private double preco_desconto;

    private boolean sob_prescricao;

    private boolean retencao;

    private boolean termolabel;

    @OneToOne(mappedBy = "medicamento", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private MedicamentoInjetavel medicamentoInjetavel;
}
