package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoDTO> getWithCriteria(PedidoSearchInputDTO pedidoSearchInputDTO) {
        return service.getPedidosWithCriteria(pedidoSearchInputDTO);
    }

    @PostMapping(name = "newPedido/{id_cliente}")
    public PedidoDTO create(@RequestParam Long id_cliente) {
        return service.createPedido(id_cliente);
    }

}
