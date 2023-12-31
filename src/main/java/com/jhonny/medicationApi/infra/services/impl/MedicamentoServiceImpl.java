package com.jhonny.medicationApi.infra.services.impl;

import com.jhonny.medicationApi.domain.builders.MedicamentoBuilder;
import com.jhonny.medicationApi.domain.builders.MedicamentoInjetavelBuilder;
import com.jhonny.medicationApi.domain.builders.MedicamentoSobPrescricaoBuilder;
import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.infra.repositories.MedicamentoInjetavelRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoSobPrescricaoRepository;
import com.jhonny.medicationApi.infra.services.MedicamentoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicamentoServiceImpl implements MedicamentoService {

    @NonNull
    MedicamentoBuilder medicamentoBuilder;
    @NonNull
    MedicamentoInjetavelBuilder medicamentoInjetavelBuilder;
    @NonNull
    MedicamentoSobPrescricaoBuilder medicamentoSobPrescricaoBuilder;

    @NonNull
    MedicamentoRepository medicamentoRepository;
    @NonNull
    MedicamentoSobPrescricaoRepository medicamentoSobPrescricaoRepository;
    @NonNull
    MedicamentoInjetavelRepository medicamentoInjetavelRepository;


    @Override
    public MedicamentoDTO saveMedicamento(MedicamentoInputDTO dto) {
            dto.setId(null);

        try {
            if (Objects.nonNull(dto.getInjetavel()) && dto.getInjetavel()) {
                MedicamentoInjetavel responseEntity = medicamentoInjetavelRepository.save(medicamentoInjetavelBuilder.dtoToEntity(dto));
                return medicamentoInjetavelBuilder.entityToDto(responseEntity);
            } else if (Objects.nonNull(dto.getSobPrescricao()) && dto.getSobPrescricao()) {
                MedicamentoSobPrescricao responseEntity = medicamentoSobPrescricaoRepository.save(medicamentoSobPrescricaoBuilder.dtoToEntity(dto));
                return medicamentoSobPrescricaoBuilder.entityToDto(responseEntity);
            } else {
                Medicamento responseEntity = medicamentoRepository.save(medicamentoBuilder.dtoToEntity(dto));
                return medicamentoBuilder.entityToDto(responseEntity);
            }
        } catch (NullPointerException e) {
            throw new NullPointerException("Required field set as null.");
        }
    }

    @Override
    public Page<MedicamentoDTO> getMedicamentosWithCriteria(MedicamentoSearchInputDTO dto) {
        Pageable pageable = PageRequest.of(
                Objects.isNull(dto.getPage())? 0 : dto.getPage(),
                Objects.isNull(dto.getSize())? 15 : dto.getSize()
        );
        List<MedicamentoDTO> responseDTO = medicamentoRepository.findAllWithCriteria(dto, pageable)
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
        Integer responseSize = medicamentoRepository.resultsCount(dto);

        Page<MedicamentoDTO> pageResult = new PageImpl<>(responseDTO, pageable, responseSize);

        return pageResult;
    }

    @Override
    public MedicamentoDTO updateMedicamento(MedicamentoInputDTO dto, Long id) {
        Medicamento medicamentoToUpdate;
        if (id == null) {
            throw new NullPointerException("'id' can not be null for this method.");
        } else {
            medicamentoToUpdate = medicamentoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Medication not found for given id."));
        }

        try {
            if ((Objects.nonNull(dto.getInjetavel()) && dto.getInjetavel()) || Objects.nonNull(dto.getTipoAplicacao())) {
                MedicamentoInjetavel responseEntity = medicamentoInjetavelRepository.save(medicamentoInjetavelBuilder.dtoToEntity((MedicamentoSearchInputDTO) dto, (MedicamentoInjetavel) medicamentoToUpdate));
                return medicamentoInjetavelBuilder.entityToDto(responseEntity);
            } else if ((Objects.nonNull(dto.getSobPrescricao()) && dto.getSobPrescricao()) || Objects.nonNull(dto.getRetencao())) {
                MedicamentoSobPrescricao responseEntity = medicamentoSobPrescricaoRepository.save(medicamentoSobPrescricaoBuilder.dtoToEntity((MedicamentoSearchInputDTO) dto, (MedicamentoSobPrescricao) medicamentoToUpdate));
                return medicamentoSobPrescricaoBuilder.entityToDto(responseEntity);
            } else {
                Medicamento responseEntity = medicamentoRepository.save(medicamentoBuilder.dtoToEntity((MedicamentoSearchInputDTO) dto, medicamentoToUpdate));
                return medicamentoBuilder.entityToDto(responseEntity);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("Invalid or null data provided.");
        }
    }

    @Override
    public MedicamentoDTO deleteMedicamento(Long id) {
        Medicamento medicamentoToDelete = medicamentoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Medication not found for given id."));
        medicamentoRepository.deleteById(id);
        if (medicamentoToDelete instanceof MedicamentoInjetavel) {
            return medicamentoInjetavelBuilder.entityToDto((MedicamentoInjetavel) medicamentoToDelete);
        }
        if (medicamentoToDelete instanceof MedicamentoSobPrescricao) {
            return medicamentoSobPrescricaoBuilder.entityToDto((MedicamentoSobPrescricao) medicamentoToDelete);
        }
        else {
            return medicamentoBuilder.entityToDto(medicamentoToDelete);
        }
    }
}
