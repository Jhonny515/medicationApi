package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.models.StatusPedido;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import com.jhonny.medicationApi.dtos.StatusPedidoDTO;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.repositories.repositories.MedicamentoRepository;
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
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    private MedicamentoRepository medicamentoRepository;
    @Mock
    private PedidoBuilder pedidoBuilder;

    Long idCliente;
    Pedido pedido;
    PedidoSearchInputDTO pedidoSearchInputDTO;

    @BeforeEach
    void setUp() {
        idCliente = 1L;
        pedido = Pedido.builder()
                .id(1L)
                .id_cliente(idCliente)
                .build();
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().build();
    }

    @Test
    public void createPedido_expectInvokeRepositoryWithNoExceptions() {
        when(pedidoRepository.save(any()))
                .thenReturn(pedido);

        when(statusPedidoRepository.getReferenceById(any()))
                .thenReturn(new StatusPedido(1, null, "teste"));

        System.out.println("Initiating method 'createPedido()' at PedidoService.......");
        assertDoesNotThrow(() -> service.createPedido(idCliente));
        System.out.println("method 'createPedido() executed!");

        verify(pedidoRepository).save(any());
        verifyNoMoreInteractions(pedidoRepository);
        System.out.println("PedidoRepository was only invoked once.");

        verify(statusPedidoRepository).getReferenceById(any());
        verifyNoMoreInteractions(statusPedidoRepository);
        System.out.println("StatusPedidoRepository was only invoked once.");

    }

    @Test
    public void getPedidosWithCriteria_expectNoExceptions() {
        List<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAllWithCriteria(any()))
                .thenReturn(pedidoList);

        System.out.println("Initiating method 'getPedidosWithCriteria()' at PedidoService.......");

        // Test for 'NullPointerException'
        System.out.println("Testing for 'NullPointerException'");
        assertDoesNotThrow(() ->
                service.getPedidosWithCriteria(pedidoSearchInputDTO)
        );
        System.out.println("'getPedidosWithCriteria()' executed with no exceptions");

        // Test for any exception
        System.out.println("Test for any exception");
        pedidoList.add(pedido);
        assertDoesNotThrow(() ->
                service.getPedidosWithCriteria(pedidoSearchInputDTO)
        );
        System.out.println("'getPedidosWithCriteria()' executed with no exceptions");
    }

    @Test
    public void addItemToCart_expectNoExceptionsWhenTheClientHasNoPedidos() {
        when(pedidoBuilder.dtoToEntity(any()))
                .thenReturn(Pedido.builder()
                        .id_cliente(idCliente)
                        .id(1L)
                        .status(StatusPedido.builder().build())
                        .build());
        when(medicamentoRepository.getReferenceById(any()))
                .thenReturn(Medicamento.builder().id(1L).build());

        assertDoesNotThrow(() ->
                service.addItemToCart(idCliente, 1L)
        );

    }
}
