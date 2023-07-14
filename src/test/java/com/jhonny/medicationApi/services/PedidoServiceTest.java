package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.domain.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.ItensCarrinho;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.models.StatusPedido;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.repositories.ItensCarrinhoRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.infra.repositories.PedidoRepository;
import com.jhonny.medicationApi.infra.repositories.StatusPedidoRepository;
import com.jhonny.medicationApi.infra.services.impl.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    private ItensCarrinhoRepository itensCarrinhoRepository;
    @Mock
    private PedidoBuilder pedidoBuilder;

    Long idCliente;
    Pedido pedido;
    PedidoSearchInputDTO pedidoSearchInputDTO;
    Medicamento medicamento;
    List<Medicamento> mockedMedicamentoList;
    List<ItensCarrinho> mockedItensList;
    List<Pedido> mockedPedidosList;

    @BeforeEach
    void setUp() {
        idCliente = 1L;
        pedido = Pedido.builder()
                .id(1L)
                .id_cliente(idCliente)
                .build();
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().build();
        medicamento = Medicamento.builder().id(1L).build();

        mockedMedicamentoList = new ArrayList<>();
        mockedItensList = new ArrayList<>();
        mockedPedidosList = new ArrayList<>();
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
        Pageable pageable = PageRequest.of(0, 15);
        List<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAllWithCriteria(any(), any()))
                .thenReturn(pedidoList);

        System.out.println("Initiating method 'getPedidosWithCriteria()' at PedidoService.......");

        // Test for 'NullPointerException'
        System.out.println("Testing for 'NullPointerException'");
        assertDoesNotThrow(() ->
                service.getPedidosWithCriteria(pedidoSearchInputDTO, pageable)
        );
        System.out.println("'getPedidosWithCriteria()' executed with no exceptions");

        // Test for any exception
        System.out.println("Test for any exception");
        pedidoList.add(pedido);
        assertDoesNotThrow(() ->
                service.getPedidosWithCriteria(pedidoSearchInputDTO, pageable)
        );
        System.out.println("'getPedidosWithCriteria()' executed with no exceptions");
    }

    @Test
    public void addItemToCart_expectNoExceptionsWhenTheClientHasNoPedidos() {
        mockedItensList.add(ItensCarrinho.builder()
                        .id(1L).pedido(1L).medicamento(1L).qnt(1)
                .build());

        when(pedidoBuilder.dtoToEntity(any()))
                .thenReturn(Pedido.builder()
                        .id_cliente(idCliente)
                        .id(1L)
                        .status(StatusPedido.builder().build())
                        .build());
        when(medicamentoRepository.getReferenceById(any()))
                .thenReturn(Medicamento.builder().id(1L).build());
        when(itensCarrinhoRepository.findAll()).thenReturn(mockedItensList);

        assertDoesNotThrow(() ->
                service.addItemToCart(idCliente, 1L, 1)
        );

    }

    @Test
    public void alterItemQtd_expectNoExceptionsAndIncreaseTheItemQtd() {
        mockedItensList.add(ItensCarrinho.builder()
                .id(1L).pedido(1L).medicamento(1L).qnt(1)
                .build());
        mockedPedidosList.add(pedido);

        when(pedidoRepository.findAllWithCriteria(any(), any())).thenReturn(mockedPedidosList);
        when(itensCarrinhoRepository.findAll()).thenReturn(mockedItensList);

        System.out.println("Initiating method 'alterItemQtd()' at PedidoService.......");
        assertDoesNotThrow(()->service.alterItemQtd(idCliente, 1L, 3));
        System.out.println("Method was executed with no exceptions.");

        assertThat(Objects.equals(mockedItensList.get(0).getQnt(), 3));
        System.out.println("Expected quantity on mockedItemCarrinho: 3");
        System.out.println("Actual quantity on mockedItemCarrinho: " + mockedItensList.get(0).getQnt());

    }

    @Test
    public void deleteItemFromCart_expectNoExceptionsAndMedicamentoDeletedFromPedido() {
        mockedMedicamentoList.add(medicamento);
        pedido.setMedicamentos(mockedMedicamentoList);
        mockedPedidosList.add(pedido);

        when(pedidoRepository.findAllWithCriteria(any(), any())).thenReturn(mockedPedidosList);

        System.out.println("Initiating method 'deleteItemFromCart()' at PedidoService.......");
        assertDoesNotThrow(()->service.deleteItemFromCart(idCliente,1L));
        System.out.println("Method was executed with no exceptions.");

        assertThat(mockedMedicamentoList.isEmpty());
        System.out.println("Medicamento was deleted from Pedido.");
    }
}
