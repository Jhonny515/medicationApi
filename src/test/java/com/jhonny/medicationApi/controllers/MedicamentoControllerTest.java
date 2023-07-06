package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.services.MedicamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicamentoControllerTest {
    @InjectMocks
    MedicamentoController medicamentoController;

    @Mock
    private MedicamentoService service;

    MockMvc mockMvc;
    MedicamentoInputDTO medicamentoInputDTO;
    MedicamentoSearchInputDTO medicamentoSearchInputDTO;

    @BeforeEach
    public void setUp() {
        medicamentoInputDTO = MedicamentoInputDTO.builder()
                .id(1L)
                .nome("Any")
                .principio_ativo("Any 500mg")
                .descricao("Medicamento qualquer")
                .marca("ACME")
                .fabricante("ACME")
                .preco(10.15)
                .preco_desconto(10.15)
                .sobPrescricao(true)
                .retencao(false)
                .injetavel(true)
                .tipoAplicacao(TipoAplicacao.SUBCUTANEA)
                .termolabel(true)
                .build();

        medicamentoSearchInputDTO = MedicamentoSearchInputDTO.builder()
                .id(1L)
                .nome("Any")
                .principio_ativo("Any 500mg")
                .descricao("Medicamento qualquer")
                .marca("ACME")
                .fabricante("ACME")
                .preco(10.15)
                .preco_desconto(10.15)
                .sobPrescricao(true)
                .retencao(false)
                .injetavel(true)
                .tipoAplicacao(TipoAplicacao.SUBCUTANEA)
                .termolabel(true)
                .orderBy("nome")
                .order("asc")
                .build();
    }

    @Test
    public void getWithCriteria_expectInvocationToServiceWithNoExceptions() {
        when(service.getMedicamentosWithCriteria(any())).thenReturn(Collections.singletonList(new MedicamentoDTO()));

        System.out.println("Initiating 'MedicamentoControler.getWithCriteria()' method.......");
        medicamentoController.getWithCriteria(medicamentoSearchInputDTO);
        System.out.println("'MedicamentoControler.getWithCriteria()' method was initiated.");

        verify(service).getMedicamentosWithCriteria(any());
        System.out.println("Service Layer was invoked.");
        verifyNoMoreInteractions(service);
        System.out.println("Service Layer was only invoked once.");
    }

    @Test
    public void save_expectInvocationToServiceWithNoExceptions(){
        when(service.saveMedicamento(any()))
                .thenReturn(new MedicamentoDTO());

        System.out.println("Initiating 'MedicamentoControler.save()' method.......");
        assertDoesNotThrow(()->
                medicamentoController.save(medicamentoInputDTO));
        System.out.println("Service Layer was invoked.");
    }

    @Test
    public void update_expectInvocationToServiceWithNoExceptions() {
        when(service.updateMedicamento(medicamentoInputDTO))
                .thenReturn(new MedicamentoDTO());

        System.out.println("Initiating 'MedicamentoControler.save()' method.......");
        assertDoesNotThrow(()->
                medicamentoController.update(medicamentoInputDTO)
        );
        System.out.println("Service Layer was invoked.");
    }

    @Test
    public void delete_expectInvocationToServiceWithNoExceptions() {
        when(service.deleteMedicamento(any())).thenReturn(new MedicamentoDTO());

        assertDoesNotThrow(()->
                medicamentoController.delete(1L)
        );
        System.out.println("Service Layer was invoked.");
    }
}
