package com.jhonny.medicationApi.infra.repositories.custom;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;

import java.util.List;

public interface MedicamentoCustomRepository {

    List<Medicamento> findAllWithCriteria(MedicamentoInputDTO dto);
}
