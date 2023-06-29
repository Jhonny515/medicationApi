package com.jhonny.medicationApi.domain.models;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_med_injetavel")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoInjetavel {

    @Id
    @Column(name = "medicamento_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    private TipoAplicacao tipo_aplicacao;
}
