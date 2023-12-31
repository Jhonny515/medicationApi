package com.jhonny.medicationApi.infra.services;

import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component @Service
public interface MedicamentoService {

    MedicamentoDTO saveMedicamento(MedicamentoInputDTO dto);
    Page<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoSearchInputDTO dto);
    MedicamentoDTO updateMedicamento(MedicamentoInputDTO dto, Long id);
    MedicamentoDTO deleteMedicamento(Long id);
}
