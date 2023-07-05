package com.jhonny.medicationApi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
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
}
