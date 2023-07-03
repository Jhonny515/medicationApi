package com.jhonny.medicationApi.services;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.search.MedicamentoSearchInputDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component @Service
public interface MedicamentoService {

    MedicamentoDTO saveMedicamento(MedicamentoDTO dto);
    List<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoSearchInputDTO dto);
}
