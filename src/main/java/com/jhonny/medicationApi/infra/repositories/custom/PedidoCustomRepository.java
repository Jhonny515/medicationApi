package com.jhonny.medicationApi.infra.repositories.custom;

import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;

import java.util.List;

public interface PedidoCustomRepository {
    List<Pedido> findAllWithCriteria(PedidoSearchInputDTO dto);
}
