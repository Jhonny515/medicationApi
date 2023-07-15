package com.jhonny.medicationApi.domain.builders;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.MedicamentoSobPrescricaoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoSobPrescricaoBuilder{

    public MedicamentoSobPrescricao dtoToEntity(MedicamentoInputDTO dto) {
        MedicamentoSobPrescricao turnedEntity = MedicamentoSobPrescricao.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .termolabel(dto.getTermolabel())
                .retencao((dto.getRetencao()))
                .build();
        return turnedEntity;
    }

    public MedicamentoDTO entityToDto(MedicamentoSobPrescricao entity) {
        MedicamentoDTO turnedDTO = MedicamentoDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .principio_ativo(entity.getPrincipio_ativo())
                .descricao(entity.getDescricao())
                .marca(entity.getMarca())
                .fabricante(entity.getFabricante())
                .preco(entity.getPreco())
                .preco_desconto(entity.getPreco_desconto())
                .porcentagem_desconto((1 - (entity.getPreco_desconto() / entity.getPreco())) * 100)
                .termolabel(entity.isTermolabel())
                .sob_prescricao( new MedicamentoSobPrescricaoDTO().builder()
                        .retencao(entity.isRetencao()).build())
                .build();

        return turnedDTO;
    }

    public MedicamentoSobPrescricao dtoToEntity(MedicamentoSearchInputDTO dto, MedicamentoSobPrescricao entity) {
        entity = MedicamentoSobPrescricao.builder()
                .id(entity.getId())
                .nome(dto.getNome()==null? entity.getNome() : dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo()==null? entity.getPrincipio_ativo() : dto.getPrincipio_ativo())
                .descricao(dto.getDescricao()==null? entity.getDescricao() : dto.getDescricao())
                .marca(dto.getMarca()==null? entity.getMarca() : dto.getMarca())
                .fabricante(dto.getFabricante()==null? entity.getFabricante() : dto.getFabricante())
                .preco(dto.getPreco()==null? entity.getPreco() : dto.getPreco())
                .preco_desconto(dto.getPreco_desconto()==null? entity.getPreco_desconto() : dto.getPreco_desconto())
                .termolabel(dto.getTermolabel() == null? entity.isTermolabel() : dto.getTermolabel())
                .retencao(dto.getRetencao() == null? entity.isRetencao() : dto.getRetencao())
                .build();

        return entity;
    }

    public Medicamento dtoToEntity(MedicamentoDTO dto) {
        return MedicamentoSobPrescricao.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .termolabel(dto.getTermolabel())
                .retencao(dto.getSob_prescricao().getRetencao())
                .build();
    }
}
