package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component @Service
public interface PedidoService {
    List<PedidoDTO> getAllPedidos();
    PedidoDTO getPedido(Long id);
    PedidoDTO createPedido(Long idCliente);
}
