package com.jhonny.medicationApi.repositories.repositories.custom;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;

import java.util.List;

public interface MedicamentoCustomRepository {

    List<Medicamento> findAllWithCriteria(MedicamentoSearchInputDTO dto);
}