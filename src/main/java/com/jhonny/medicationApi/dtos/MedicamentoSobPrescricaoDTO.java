package com.jhonny.medicationApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoSobPrescricaoDTO {

    protected boolean retencao;
    protected MedicamentoInjetavelDTO injetavel;
}
