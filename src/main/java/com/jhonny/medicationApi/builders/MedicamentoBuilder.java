package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoBuilder {

    public Medicamento dtoToEntity(MedicamentoInputDTO dto) {
        Medicamento turnedEntity = Medicamento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .termolabel(dto.getTermolabel())
                .build();

        return turnedEntity;
    }

    public MedicamentoDTO entityToDto(Medicamento entity) {
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
                .build();

        return turnedDTO;
    }

    public Medicamento dtoToEntity(MedicamentoSearchInputDTO dto, Medicamento entity) {
        entity = Medicamento.builder()
                .id(entity.getId())
                .nome(dto.getNome()==null? entity.getNome() : dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo()==null? entity.getPrincipio_ativo() : dto.getPrincipio_ativo())
                .descricao(dto.getDescricao()==null? entity.getDescricao() : dto.getDescricao())
                .marca(dto.getMarca()==null? entity.getMarca() : dto.getMarca())
                .fabricante(dto.getFabricante()==null? entity.getFabricante() : dto.getFabricante())
                .preco(dto.getPreco()==null? entity.getPreco() : dto.getPreco())
                .preco_desconto(dto.getPreco_desconto()==null? entity.getPreco_desconto() : dto.getPreco_desconto())
                .termolabel(dto.getTermolabel() == null? entity.isTermolabel() : dto.getTermolabel())
                .build();

        return entity;
    }
}
