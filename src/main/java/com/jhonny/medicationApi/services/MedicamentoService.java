package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component @Service
public interface MedicamentoService {

    MedicamentoDTO saveMedicamento(MedicamentoDTO dto);
    List<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoSearchInputDTO dto);
    MedicamentoDTO updateMedicamento(MedicamentoInputDTO dto);
    MedicamentoDTO deleteMedicamento(Long id);
}
