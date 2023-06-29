package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicamentoBuilder implements MainBuilder<Medicamento, MedicamentoDTO>{
    @Override
    public MedicamentoDTO entityToDTO(Medicamento entity) {
        return null;
    }

    @Override
    public Medicamento dtoToEntity(MedicamentoDTO dto) {
        return Medicamento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .principio_ativo(dto.getPrincipio_ativo())
                .descricao(dto.getDescricao())
                .marca(dto.getMarca())
                .fabricante(dto.getFabricante())
                .preco(dto.getPreco())
                .preco_desconto(dto.getPreco_desconto())
                .sob_prescricao(dto.isSob_prescricao())
                .retencao(dto.isRetencao())
                .termolabel(dto.isTermolabel())
                .build();
    }
}
