package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.models.StatusPedido;
import com.jhonny.medicationApi.repositories.repositories.PedidoRepository;
import com.jhonny.medicationApi.repositories.repositories.StatusPedidoRepository;
import com.jhonny.medicationApi.services.impl.PedidoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    PedidoServiceImpl service;

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private StatusPedidoRepository statusPedidoRepository;
    @Mock
    private PedidoBuilder pedidoBuilder;

    Long idCliente;
    Pedido pedido;

    @BeforeEach
    void setUp() {
        idCliente = 1L;
        pedido = Pedido.builder()
                .id(1L)
                .id_cliente(idCliente)
                .build();
    }

    @Test
    public void createPedido_expectInvokeRepositoryWithNoExceptions() {
        when(pedidoRepository.save(any()))
                .thenReturn(pedido);

        when(statusPedidoRepository.getReferenceById(any()))
                .thenReturn(new StatusPedido(1,null, "teste"));

        System.out.println("Initiating method 'createPedido()' at PedidoService.......");
        Assertions.assertDoesNotThrow(()->service.createPedido(idCliente));
        System.out.println("method 'createPedido() executed!");

        verify(pedidoRepository).save(any());
        verifyNoMoreInteractions(pedidoRepository);
        System.out.println("PedidoRepository was only invoked once.");

        verify(statusPedidoRepository).getReferenceById(any());
        verifyNoMoreInteractions(statusPedidoRepository);
        System.out.println("StatusPedidoRepository was only invoked once.");

    }

}
