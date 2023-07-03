package com.jhonny.medicationApi.dtos.search;

import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import com.jhonny.medicationApi.dtos.MedicamentoSobPrescricaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class MedicamentoSearchInputDTO {
    protected Long id;
    protected String nome;
    protected String principio_ativo;
    protected String descricao;
    protected String marca;
    protected String fabricante;
    protected Boolean sobPrescricao;
    protected Boolean retencao;
    protected Boolean injetavel;
    protected TipoAplicacao tipoAplicacao;
    protected Boolean termolabel;
}
