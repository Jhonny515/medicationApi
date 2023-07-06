package com.jhonny.medicationApi.services.impl;

import com.jhonny.medicationApi.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.repositories.repositories.PedidoRepository;
import com.jhonny.medicationApi.repositories.repositories.StatusPedidoRepository;
import com.jhonny.medicationApi.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private StatusPedidoRepository statusPedidoRepository;
    @Autowired
    private PedidoBuilder pedidoBuilder;

    @Override
    public List<PedidoDTO> getAllPedidos() {
        return null;
    }

    @Override
    public PedidoDTO getPedido(Long id) {
        return null;
    }

    @Override
    public PedidoDTO createPedido(Long idCliente) {
        Pedido newPedido = Pedido.builder()
                .id_cliente(idCliente)
                .status(statusPedidoRepository.getReferenceById(1))
                .build();

        return pedidoBuilder.entityToDto(pedidoRepository.save(newPedido));
    }
}
