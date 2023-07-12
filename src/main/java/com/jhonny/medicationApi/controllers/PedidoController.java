package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.services.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(name = "search")
    public List<PedidoDTO> getWithCriteria(PedidoSearchInputDTO pedidoSearchInputDTO) {
        return service.getPedidosWithCriteria(pedidoSearchInputDTO);
    }

    @PostMapping(name = "addToCart")
    public HttpStatus addItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, @RequestParam int qtd) {
        return service.addItemToCart(idCliente, idMedicamento, qtd);
    }

    @PutMapping(name = "alter")
    public HttpStatus alterItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, @RequestParam int qtd) {
        return service.alterItemQtd(idCliente, idMedicamento, qtd);
    }

    @DeleteMapping(name = "delete")
    public HttpStatus deleteItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento) {
        return service.deleteItemFromCart(idCliente, idMedicamento);
    }

}
