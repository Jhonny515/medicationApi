package com.jhonny.medicationApi.services.impl;

import com.jhonny.medicationApi.builders.MedicamentoBuilder;
import com.jhonny.medicationApi.builders.MedicamentoInjetavelBuilder;
import com.jhonny.medicationApi.builders.MedicamentoSobPrescricaoBuilder;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.infra.repositories.MedicamentoInjetavelRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoSobPrescricaoRepository;
import com.jhonny.medicationApi.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    MedicamentoBuilder medicamentoBuilder;
    @Autowired
    MedicamentoInjetavelBuilder medicamentoInjetavelBuilder;
    @Autowired
    MedicamentoSobPrescricaoBuilder medicamentoSobPrescricaoBuilder;

    @Autowired
    MedicamentoRepository medicamentoRepository;
    @Autowired
    MedicamentoSobPrescricaoRepository medicamentoSobPrescricaoRepository;
    @Autowired
    MedicamentoInjetavelRepository medicamentoInjetavelRepository;


    @Override
    public MedicamentoDTO saveMedicamento(MedicamentoDTO dto) {
            dto.setId(null);

            if (Objects.nonNull(dto.getSob_prescricao()) && Objects.nonNull(dto.getSob_prescricao().getInjetavel())) {
                MedicamentoInjetavel responseEntity = medicamentoInjetavelRepository.save( medicamentoInjetavelBuilder.dtoToEntity(dto));
                return medicamentoInjetavelBuilder.entityToDto(responseEntity);
            } else if (Objects.nonNull(dto.getSob_prescricao())) {
                MedicamentoSobPrescricao responseEntity = medicamentoSobPrescricaoRepository.save( medicamentoSobPrescricaoBuilder.dtoToEntity(dto));
                return medicamentoSobPrescricaoBuilder.entityToDto(responseEntity);
            } else {
                Medicamento responseEntity =  medicamentoRepository.save(medicamentoBuilder.dtoToEntity(dto));
                return medicamentoBuilder.entityToDto(responseEntity);
        }
    }

    @Override
    public List<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoDTO dto) {
        List<MedicamentoDTO> responseDTO = medicamentoRepository.findAllWithCriteria(dto)
                .stream().map(medicamentoBuilder::entityToDto).collect(Collectors.toList());
        return responseDTO;
    }

}
