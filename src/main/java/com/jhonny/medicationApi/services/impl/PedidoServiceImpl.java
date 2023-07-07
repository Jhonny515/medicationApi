package com.jhonny.medicationApi.services.impl;

import com.jhonny.medicationApi.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.repositories.repositories.PedidoRepository;
import com.jhonny.medicationApi.repositories.repositories.StatusPedidoRepository;
import com.jhonny.medicationApi.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private StatusPedidoRepository statusPedidoRepository;
    @Autowired
    private PedidoBuilder pedidoBuilder;

    @Override
    public List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto) {
        return pedidoRepository.findAllWithCriteria(dto)
                .stream().map(pedido -> pedidoBuilder.entityToDto(pedido))
                .collect(Collectors.toList());
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
