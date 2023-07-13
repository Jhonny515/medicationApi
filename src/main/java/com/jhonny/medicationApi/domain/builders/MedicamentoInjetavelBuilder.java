package com.jhonny.medicationApi.domain.builders;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.MedicamentoInjetavelDTO;
import com.jhonny.medicationApi.domain.dtos.MedicamentoSobPrescricaoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoInjetavelBuilder {

    public MedicamentoInjetavel dtoToEntity(MedicamentoInputDTO dto) {
        MedicamentoInjetavel turnedEntity = MedicamentoInjetavel.builder()
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
                .tipo_aplicacao(dto.getTipoAplicacao())
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
                .porcentagem_desconto((1 - (entity.getPreco_desconto() / entity.getPreco())) * 100)
                .termolabel(entity.isTermolabel())
                .sob_prescricao( new MedicamentoSobPrescricaoDTO().builder()
                        .retencao(entity.isRetencao())
                        .injetavel(new MedicamentoInjetavelDTO().builder()
                                .tipoAplicacao(entity.getTipo_aplicacao().toString())
                                .build())
                        .build())
                .build();

        return turnedDTO;
    }

    public MedicamentoInjetavel dtoToEntity(MedicamentoSearchInputDTO dto, MedicamentoInjetavel entity) {
        entity = MedicamentoInjetavel.builder()
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
                .tipo_aplicacao(dto.getTipoAplicacao()==null? entity.getTipo_aplicacao() : dto.getTipoAplicacao())
                .build();

        return entity;
    }

    public Medicamento dtoToEntity(MedicamentoDTO dto) {
        return MedicamentoInjetavel.builder()
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
                .tipo_aplicacao(TipoAplicacao.valueOf(dto.getSob_prescricao().getInjetavel().getTipoAplicacao()))
                .build();
    }
}
