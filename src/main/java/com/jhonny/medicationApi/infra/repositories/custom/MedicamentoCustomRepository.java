package com.jhonny.medicationApi.infra.repositories.custom;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicamentoCustomRepository {

    List<Medicamento> findAllWithCriteria(MedicamentoSearchInputDTO dto, Pageable pageable);
    Integer resultsCount(MedicamentoSearchInputDTO dto);
}
