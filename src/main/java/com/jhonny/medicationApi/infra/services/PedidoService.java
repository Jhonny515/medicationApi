package com.jhonny.medicationApi.infra.services;

import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component @Service
public interface PedidoService {
    List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto);
    PedidoDTO createPedido(Long idCliente);
    HttpStatus addItemToCart(Long idCliente, Long idMedicamento, int qtd);
    HttpStatus alterItemQtd(Long idCliente, Long idMedicamento, int qtd);
    HttpStatus deleteItemFromCart(Long idCliente, Long idMedicamento);
}
