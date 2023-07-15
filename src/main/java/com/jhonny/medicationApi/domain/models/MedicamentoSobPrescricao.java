package com.jhonny.medicationApi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_med_sob_prescricao")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "id_medicamento")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoSobPrescricao extends Medicamento {

    @NotNull
    private boolean retencao;
}
