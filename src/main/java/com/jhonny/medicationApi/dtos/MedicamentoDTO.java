package com.jhonny.medicationApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoDTO {
    protected Long id;
    protected String nome;
    protected String principio_ativo;
    protected String descricao;
    protected String marca;
    protected String fabricante;
    protected double preco;
    protected double preco_desconto;
    protected double porcentagem_desconto;
    protected MedicamentoSobPrescricaoDTO sob_prescricao;
    protected Boolean termolabel;

    protected MedicamentoDTO(MedicamentoDTO medicamentoDTO) {
        id = medicamentoDTO.getId();
        nome = medicamentoDTO.getNome();
        principio_ativo = medicamentoDTO.getPrincipio_ativo();
        descricao = medicamentoDTO.getDescricao();
        marca = medicamentoDTO.getMarca();
        fabricante = medicamentoDTO.getFabricante();
        preco = medicamentoDTO.getPreco();
        preco_desconto = medicamentoDTO.getPreco_desconto();
        porcentagem_desconto = medicamentoDTO.getPorcentagem_desconto();
        sob_prescricao = medicamentoDTO.getSob_prescricao();
        termolabel = medicamentoDTO.getTermolabel();
    }
}
