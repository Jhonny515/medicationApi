package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping("search")
    public ResponseEntity<List<PedidoDTO>> getWithCriteria(@ParameterObject PedidoSearchInputDTO pedidoSearchInputDTO, @ParameterObject @PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPedidosWithCriteria(pedidoSearchInputDTO, pageable));
    }

    @PostMapping("addToCart")
    @Transactional
    public ResponseEntity addItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.addItemToCart(idCliente, idMedicamento);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        return ResponseEntity.created(uri).body(idPedido);
    }

    @PutMapping("alter")
    @Transactional
    public ResponseEntity alterItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, @RequestParam int qtd, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.alterItemQtd(idCliente, idMedicamento, qtd);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        return ResponseEntity.created(uri).body(idPedido);
    }

    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity deleteItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.deleteItemFromCart(idCliente, idMedicamento);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        return ResponseEntity.created(uri).body(idPedido);
    }

    @PutMapping("checkout")
    public ResponseEntity checkout(@RequestParam Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(service.checkoutPedido(idCliente));
    }

}
