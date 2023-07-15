package com.jhonny.medicationApi.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoPedidoDTO extends MedicamentoDTO{
    protected int qnt;

    public MedicamentoPedidoDTO(MedicamentoDTO medicamentoDTO, int qnt) {
        super(medicamentoDTO);
        this.qnt = qnt;
    }
}
