package com.jhonny.medicationApi.domain.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoSearchInputDTO extends MedicamentoInputDTO {
    protected String orderBy;
    protected String order;
    protected Double higherThan;
    protected Double lowerThan;
}
