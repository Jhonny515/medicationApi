package com.jhonny.medicationApi.services.impl;

import com.jhonny.medicationApi.builders.MedicamentoBuilder;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    MedicamentoBuilder medicamentoBuilder;

    MedicamentoRepository medicamentoRepository;


    @Override
    public Medicamento saveMedicamento(MedicamentoDTO dto) {
        dto.setId(null);

        return medicamentoRepository.save(medicamentoBuilder.dtoToEntity(dto));
    }

    @Override
    public List<Medicamento> getAllMedicamentosWithCriteria() {
        return medicamentoRepository.findAll();
    }

}
