package com.jhonny.medicationApi.infra.repositories.custom;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;

import java.util.List;

public interface MedicamentoSobPrescricaoCustomRepository {

    List<MedicamentoSobPrescricao> findAllWithCriteria(MedicamentoDTO dto);
}
