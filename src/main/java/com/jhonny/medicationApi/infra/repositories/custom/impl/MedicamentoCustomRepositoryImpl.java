package com.jhonny.medicationApi.infra.repositories.custom.impl;


import com.jhonny.medicationApi.domain.enums.TipoAplicacao;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.search.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.infra.repositories.CriteriaParent;
import com.jhonny.medicationApi.infra.repositories.custom.MedicamentoCustomRepository;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MedicamentoCustomRepositoryImpl extends CriteriaParent<Medicamento, MedicamentoSearchInputDTO> implements MedicamentoCustomRepository {


    @Override
    public List<Medicamento> findAllWithCriteria(MedicamentoSearchInputDTO dto) {
        return super.findAllWithCriteriaParent(dto);
    }

    @Override
    protected void filterAtributesFromEntity(MedicamentoSearchInputDTO paramDTO,
                                             CriteriaBuilder cb,
                                             Root<Medicamento> root,
                                             List<Predicate> predicates,
                                             CriteriaQuery<Medicamento> query) {
        if (Objects.nonNull(paramDTO)){
            if (Objects.nonNull(paramDTO.getId())) {
                predicates.add(cb.equal(root.get("id"), paramDTO.getId()));
            }
            if (Objects.nonNull(paramDTO.getNome())) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + paramDTO.getNome().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.getPrincipio_ativo())) {
                predicates.add(cb.like(cb.lower(root.get("principio_ativo")), "%" + paramDTO.getPrincipio_ativo().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.getDescricao())) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + paramDTO.getDescricao().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.getMarca())) {
                predicates.add(cb.like(cb.lower(root.get("marca")), "%" + paramDTO.getMarca().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.getFabricante())) {
                predicates.add(cb.like(cb.lower(root.get("fabricante")), "%" + paramDTO.getFabricante().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.getTermolabel())) {
                predicates.add(cb.equal(root.get("termolabel"), paramDTO.getTermolabel()));
            }
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<MedicamentoSobPrescricao> sobPrescricaoRoot = subquery.from(MedicamentoSobPrescricao.class);
            if (!Objects.nonNull(paramDTO.getSobPrescricao()) || paramDTO.getSobPrescricao() == true){
                    if (Objects.nonNull(paramDTO.getRetencao())){
                        subquery.select(sobPrescricaoRoot.get("id")).where(cb.equal(sobPrescricaoRoot.get("retencao"), paramDTO.getRetencao()));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    } else if (paramDTO.getSobPrescricao() == true){
                        subquery.select(sobPrescricaoRoot.get("id"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    }
                if (!Objects.nonNull(paramDTO.getInjetavel()) || paramDTO.getInjetavel()==true) {
                        Root<MedicamentoInjetavel> injetavelRoot = subquery.from(MedicamentoInjetavel.class);
                    if (Objects.nonNull(paramDTO.getTipoAplicacao())) {
                        subquery.select(injetavelRoot.get("id")).where(cb.like(cb.lower(injetavelRoot.get("tipo_aplicacao")), "%" + paramDTO.getTipoAplicacao().toString().toLowerCase() + "%"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    } else if (paramDTO.getInjetavel()==true) {
                        subquery.select(injetavelRoot.get("id"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    } else {
                        subquery.select(sobPrescricaoRoot.get("id"));
                        predicates.add(cb.not(cb.in(root.get("id")).value(subquery)));
                    }
                }
            } else {
                subquery.select(sobPrescricaoRoot.get("id"));
                predicates.add(cb.not(cb.in(root.get("id")).value(subquery)));
            }
        }
    }
}
