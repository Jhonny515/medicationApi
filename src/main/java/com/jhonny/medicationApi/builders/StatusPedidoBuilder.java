package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.StatusPedido;
import com.jhonny.medicationApi.dtos.StatusPedidoDTO;

public class StatusPedidoBuilder {

    public StatusPedidoDTO entityToDto(StatusPedido entity) {
        return StatusPedidoDTO.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .build();
    }
}
