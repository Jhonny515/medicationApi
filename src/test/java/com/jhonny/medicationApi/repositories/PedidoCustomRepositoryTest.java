package com.jhonny.medicationApi.repositories;

import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class PedidoCustomRepositoryTest {

    @Mock
    PedidoRepository customRepository;

    PedidoSearchInputDTO pedidoSearchInputDTO;

    @BeforeEach
    void setUp() {
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().build();
    }

    @Test
    public void findAllWithCriteria_expectNoExceptions() {
        Pageable pageable = PageRequest.of(0,15);
        System.out.println("Test PedidoCustomRepository.findAllWithCriteria()");
        // pedidoSearchInput is null
        assertDoesNotThrow(()->customRepository.findAllWithCriteria(pedidoSearchInputDTO, pageable));
        System.out.println("pedidoSearchInput is null = ok");

        // pedidoSearchInput has 'id'
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().id(1L).build();
        assertDoesNotThrow(()->customRepository.findAllWithCriteria(pedidoSearchInputDTO, pageable));
        System.out.println("pedidoSearchInput has id = ok");

        // pedidoSearchInput has 'id_cliente'
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().id_cliente(1L).build();
        assertDoesNotThrow(()->customRepository.findAllWithCriteria(pedidoSearchInputDTO, pageable));
        System.out.println("pedidoSearchInput has id_cliente = ok");

        // pedidoSearchInput has 'id_status'
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().id_status(1).build();
        assertDoesNotThrow(()->customRepository.findAllWithCriteria(pedidoSearchInputDTO, pageable));
        System.out.println("pedidoSearchInput has id_status = ok");

        // pedidoSearchInput has 'status'
        pedidoSearchInputDTO = PedidoSearchInputDTO.builder().status("").build();
        assertDoesNotThrow(()->customRepository.findAllWithCriteria(pedidoSearchInputDTO, pageable));
        System.out.println("pedidoSearchInput has status = ok");

    }

}
