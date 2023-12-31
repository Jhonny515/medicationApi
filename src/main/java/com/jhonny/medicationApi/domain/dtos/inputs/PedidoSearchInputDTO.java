package com.jhonny.medicationApi.domain.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PedidoSearchInputDTO {
    protected Long id;
    protected Long id_cliente;
    protected Integer id_status;
    protected String status;
}
