package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component @Service
public interface MedicamentoService {

    MedicamentoDTO saveMedicamento(MedicamentoDTO dto);
    List<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoInputDTO dto);
    MedicamentoDTO updateMedicamento(MedicamentoInputDTO dto);
}
