package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {
    @InjectMocks
    PedidoController controller;

    @Mock
    PedidoService pedidoService;

    PedidoSearchInputDTO pedidoSearchInputDTO;

    @BeforeEach
    void setUp() {
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().build();
    }

    @Test
    public void getWithCriteria_expectInvokeServiceWithNoExceptions() {
        when(pedidoService.getPedidosWithCriteria(any())).thenReturn(Collections.singletonList(new PedidoDTO()));

        System.out.println("Initiating 'PedidoControler.getWithCriteria()' method.......");
        controller.getWithCriteria(pedidoSearchInputDTO);
        System.out.println("'PedidoControler.getWithCriteria()' method was initiated.");

        verify(pedidoService).getPedidosWithCriteria(any());
        System.out.println("Service Layer was invoked.");
        verifyNoMoreInteractions(pedidoService);
        System.out.println("Service Layer was only invoked once.");
    }

    @Test
    public void addItem_expectInvokeServiceWithNoExceptions() {
        when(pedidoService.addItemToCart(any(Long.class),any(Long.class), any(Integer.class))).thenReturn(HttpStatus.OK);

        System.out.println("Initiating 'PedidoControler.addItem()' method.......");
        controller.addItem(1L, 1L, 1);
        System.out.println("'PedidoControler.addItem()' method was initiated.");

        verify(pedidoService).addItemToCart(any(Long.class),any(Long.class), any(Integer.class));
        System.out.println("Service Layer was invoked.");
        verifyNoMoreInteractions(pedidoService);
        System.out.println("Service Layer was only invoked once.");
    }

    @Test
    public void alterItem_expectInvokeServiceWithNoExceptions() {
        when(pedidoService.alterItemQtd(any(Long.class),any(Long.class), any(Integer.class))).thenReturn(HttpStatus.OK);

        System.out.println("Initiating 'PedidoControler.alterItem()' method.......");
        controller.alterItem(1L, 1L, 2);
        System.out.println("'PedidoControler.alterItem()' method was initiated.");

        verify(pedidoService).alterItemQtd(any(Long.class),any(Long.class), any(Integer.class));
        System.out.println("Service Layer was invoked.");
        verifyNoMoreInteractions(pedidoService);
        System.out.println("Service Layer was only invoked once.");
    }

    @Test
    public void deleteItem_expectInvokeServiceWithNoExceptions() {
        when(pedidoService.deleteItemFromCart(any(Long.class),any(Long.class))).thenReturn(HttpStatus.OK);

        System.out.println("Initiating 'PedidoControler.deleteItem()' method.......");
        controller.deleteItem(1L, 1L);
        System.out.println("'PedidoControler.deleteItem()' method was initiated.");

        verify(pedidoService).deleteItemFromCart(any(Long.class),any(Long.class));
        System.out.println("Service Layer was invoked.");
        verifyNoMoreInteractions(pedidoService);
        System.out.println("Service Layer was only invoked once.");
    }
}
