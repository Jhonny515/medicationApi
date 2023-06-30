package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MedicamentoBuilder {

    public Medicamento dtoToEntity(MedicamentoDTO dto) {
        Medicamento turnedEntity = Medicamento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .termolabel(dto.isTermolabel())
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
                .termolabel(entity.isTermolabel())
                .build();

        return turnedDTO;
    }
}
