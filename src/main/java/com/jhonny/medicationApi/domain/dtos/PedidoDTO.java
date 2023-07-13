package com.jhonny.medicationApi.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PedidoDTO {
    protected Long id;
    protected Long id_cliente;
    protected StatusPedidoDTO status;
    protected List<MedicamentoPedidoDTO> medicamentos;
}
