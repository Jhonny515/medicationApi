package com.jhonny.medicationApi.infra.services;

import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component @Service
public interface PedidoService {
    List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto, Pageable pageable);
    PedidoDTO createPedido(Long idCliente);
    Long addItemToCart(Long idCliente, Long idMedicamento);
    Long alterItemQtd(Long idCliente, Long idMedicamento, int qtd);
    Long deleteItemFromCart(Long idCliente, Long idMedicamento);
    PedidoDTO checkoutPedido(Long idCliente);
}
