package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component @Service
public interface PedidoService {
    List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto);
    PedidoDTO createPedido(Long idCliente);
}
