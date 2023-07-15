package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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
@Tag(name = "Pedido Controller", description = "API to control orders data.")
@RequestMapping("pedidos")
public class PedidoController {
    @NonNull
    private PedidoService service;

    @Operation(summary = "Returns a list of orders(Pedidos) filtering and ordering by zero or more criterias.")
    @ApiResponse(responseCode = "200", description = "Query was sucessful", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoDTO.class)) })
    @GetMapping("search")
    public ResponseEntity<List<PedidoDTO>> getWithCriteria(@ParameterObject PedidoSearchInputDTO pedidoSearchInputDTO, @ParameterObject @PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPedidosWithCriteria(pedidoSearchInputDTO, pageable));
    }

    @Operation(summary = "Adds a medication(Medicamento) to an order(Pedido) and returns a message.")
    @ApiResponse(responseCode = "201", description = "Item was added to cart")
    @PostMapping("addToCart")
    @Transactional
    public ResponseEntity addItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.addItemToCart(idCliente, idMedicamento);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        return ResponseEntity.created(uri).body("Item added");
    }

    @Operation(summary = "Change the quantity of a medication(Medicamento) in an order(Pedido) and returns a message.")
    @ApiResponse(responseCode = "201", description = "Item quantity altered")
    @PutMapping("alter")
    @Transactional
    public ResponseEntity alterItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, @RequestParam int qtd, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.alterItemQtd(idCliente, idMedicamento, qtd);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        return ResponseEntity.created(uri).body("Item quantity altered");
    }

    @Operation(summary = "Deletes a medication(Medicamento) from an order(Pedido) and returns a message.")
    @ApiResponse(responseCode = "200", description = "Item deleted")
    @DeleteMapping("delete")
    @Transactional
    public ResponseEntity deleteItem(@RequestParam Long idCliente, @RequestParam Long idMedicamento, UriComponentsBuilder uriBuilder) {
        Long idPedido = service.deleteItemFromCart(idCliente, idMedicamento);
        URI uri = uriBuilder.path("/pedidos/search?id={id}").buildAndExpand(idPedido).toUri();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(uri);
        responseHeaders.set("status", "DELETED");
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("Item deleted");
    }

    @Operation(summary = "Turns an order(Pedido) status to 'FINALIZADO'(completed) and returns the order.")
    @ApiResponse(responseCode = "200", description = "Pedido was completed", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = PedidoDTO.class)) })
    @PutMapping("checkout")
    public ResponseEntity checkout(@RequestParam Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(service.checkoutPedido(idCliente));
    }

}
