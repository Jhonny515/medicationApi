package com.jhonny.medicationApi.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoInjetavelDTO {

    protected String tipoAplicacao;
}
