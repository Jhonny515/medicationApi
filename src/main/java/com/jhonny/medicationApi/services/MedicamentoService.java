package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MedicamentoService {

    Medicamento saveMedicamento(MedicamentoDTO dto);
    List<Medicamento> getAllMedicamentosWithCriteria();
}
