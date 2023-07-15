package com.jhonny.medicationApi.domain.dtos.inputs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoInputDTO {
    @JsonIgnore
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
