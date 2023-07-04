package com.jhonny.medicationApi.infra.repositories.custom.impl;


import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.infra.repositories.CriteriaParent;
import com.jhonny.medicationApi.infra.repositories.custom.MedicamentoCustomRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.Objects;

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
            if (Objects.isNull(paramDTO.getSobPrescricao()) || paramDTO.getSobPrescricao()){
                    if (Objects.nonNull(paramDTO.getRetencao())){
                        subquery.select(sobPrescricaoRoot.get("id")).where(cb.equal(sobPrescricaoRoot.get("retencao"), paramDTO.getRetencao()));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    } else if (Boolean.TRUE.equals(paramDTO.getSobPrescricao())){
                        subquery.select(sobPrescricaoRoot.get("id"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    }

                    Root<MedicamentoInjetavel> injetavelRoot = subquery.from(MedicamentoInjetavel.class);
                    if (Objects.isNull(paramDTO.getInjetavel()) || paramDTO.getInjetavel()) {
                    if (Objects.nonNull(paramDTO.getTipoAplicacao())) {
                        subquery.select(injetavelRoot.get("id")).where(cb.like(cb.lower(injetavelRoot.get("tipo_aplicacao")), "%" + paramDTO.getTipoAplicacao().toString().toLowerCase() + "%"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    } else if (Boolean.TRUE.equals(paramDTO.getInjetavel())) {
                        subquery.select(injetavelRoot.get("id"));
                        predicates.add(cb.in(root.get("id")).value(subquery));
                    }
                    } else {
                        subquery.select(injetavelRoot.get("id"));
                        predicates.add(cb.not(cb.in(root.get("id")).value(subquery)));
                    }
            } else {
                subquery.select(sobPrescricaoRoot.get("id"));
                predicates.add(cb.not(cb.in(root.get("id")).value(subquery)));
            }

            // Sorting

            if (Objects.nonNull(paramDTO.getOrderBy()) && Objects.nonNull(paramDTO.getOrder())) {
                query.orderBy(
                        paramDTO.getOrder().toLowerCase().equals("asc") ?
                                cb.asc(root.get(paramDTO.getOrderBy())) :
                                cb.desc(root.get(paramDTO.getOrderBy()))
                );
            }
            if (Objects.nonNull(paramDTO.getHigherThan())) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco_desconto"), paramDTO.getHigherThan()));
            }
            if (Objects.nonNull(paramDTO.getLowerThan())) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco_desconto"), paramDTO.getLowerThan()));
            }
        }
    }
}
