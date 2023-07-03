package com.jhonny.medicationApi.dtos.inputs;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoInputDTO {
    protected Long id;
    protected String nome;
    protected String principio_ativo;
    protected String descricao;
    protected String marca;
    protected String fabricante;
    protected Double preco;
    protected Double preco_desconto;
    protected Boolean sobPrescricao;
    protected Boolean retencao;
    protected Boolean injetavel;
    protected TipoAplicacao tipoAplicacao;
    protected Boolean termolabel;
}
