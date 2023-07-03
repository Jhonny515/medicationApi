package com.jhonny.medicationApi.services.impl;

import com.jhonny.medicationApi.builders.MedicamentoBuilder;
import com.jhonny.medicationApi.builders.MedicamentoInjetavelBuilder;
import com.jhonny.medicationApi.builders.MedicamentoSobPrescricaoBuilder;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.infra.repositories.MedicamentoInjetavelRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoSobPrescricaoRepository;
import com.jhonny.medicationApi.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoInputDTO dto) {
        List<MedicamentoDTO> responseDTO = medicamentoRepository.findAllWithCriteria(dto)
                .stream().map((medicamento) -> {
                    if (medicamento instanceof MedicamentoInjetavel) {
                        return medicamentoInjetavelBuilder.entityToDto((MedicamentoInjetavel) medicamento);
                    }
                    if (medicamento instanceof MedicamentoSobPrescricao) {
                        return medicamentoSobPrescricaoBuilder.entityToDto((MedicamentoSobPrescricao) medicamento);
                    }
                    else {
                        return medicamentoBuilder.entityToDto(medicamento);
                    }
                }).collect(Collectors.toList());
        return responseDTO;
    }

    @Override
    public MedicamentoDTO updateMedicamento(MedicamentoInputDTO dto) {
        Medicamento medicamentoToUpdate;
        if (dto.getId() == null) {
            System.err.println("ID não pode estar nulo para atualização do medicamento.");
            return null; // TODO: Tratamento de Exceção
        } else {
            medicamentoToUpdate = medicamentoRepository.findById(dto.getId()).orElseThrow(); // TODO: Tratamento de Exceção
        }

        if ((Objects.nonNull(dto.getInjetavel()) && dto.getInjetavel()) || Objects.nonNull(dto.getTipoAplicacao())) {
            MedicamentoInjetavel responseEntity = medicamentoInjetavelRepository.save(medicamentoInjetavelBuilder.dtoToEntity(dto, (MedicamentoInjetavel) medicamentoToUpdate));
            return medicamentoInjetavelBuilder.entityToDto(responseEntity);
        } else if ((Objects.nonNull(dto.getSobPrescricao()) &&  dto.getSobPrescricao()) || Objects.nonNull(dto.getRetencao())) {
            MedicamentoSobPrescricao responseEntity = medicamentoSobPrescricaoRepository.save( medicamentoSobPrescricaoBuilder.dtoToEntity(dto, (MedicamentoSobPrescricao) medicamentoToUpdate));
            return medicamentoSobPrescricaoBuilder.entityToDto(responseEntity);
        } else {
            Medicamento responseEntity =  medicamentoRepository.save(medicamentoBuilder.dtoToEntity(dto, medicamentoToUpdate));
            return medicamentoBuilder.entityToDto(responseEntity);
        }
    }
}
