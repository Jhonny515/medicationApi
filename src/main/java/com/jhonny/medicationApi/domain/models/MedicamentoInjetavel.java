package com.jhonny.medicationApi.domain.models;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_med_injetavel")
@PrimaryKeyJoinColumn(name = "id_medicamento")
@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoInjetavel extends MedicamentoSobPrescricao {

    @NotNull @Enumerated(EnumType.STRING)
    private TipoAplicacao tipo_aplicacao;
}
