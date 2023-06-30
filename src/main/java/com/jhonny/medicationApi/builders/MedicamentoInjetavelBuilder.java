package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.MedicamentoInjetavelDTO;
import com.jhonny.medicationApi.dtos.MedicamentoSobPrescricaoDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoInjetavelBuilder {

    public MedicamentoInjetavel dtoToEntity(MedicamentoDTO dto) {
        MedicamentoInjetavel turnedEntity = MedicamentoInjetavel.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .termolabel(dto.isTermolabel())
                .retencao((dto.getSob_prescricao().isRetencao()))
                .tipo_aplicacao(dto.getSob_prescricao().getInjetavel().getTipoAplicacao())
                .build();
        return turnedEntity;
    }

    public MedicamentoDTO entityToDto(MedicamentoInjetavel entity) {
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
                .sob_prescricao( new MedicamentoSobPrescricaoDTO().builder()
                        .retencao(entity.isRetencao())
                        .injetavel(new MedicamentoInjetavelDTO().builder()
                                .tipoAplicacao(entity.getTipo_aplicacao())
                                .build())
                        .build())
                .build();

        return turnedDTO;
    }

}
