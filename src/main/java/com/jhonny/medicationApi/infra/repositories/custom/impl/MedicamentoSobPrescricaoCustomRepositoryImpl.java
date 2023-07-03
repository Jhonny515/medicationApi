package com.jhonny.medicationApi.infra.repositories.custom.impl;

import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.infra.repositories.CriteriaParent;
import com.jhonny.medicationApi.infra.repositories.custom.MedicamentoSobPrescricaoCustomRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public abstract class MedicamentoSobPrescricaoCustomRepositoryImpl extends CriteriaParent<MedicamentoSobPrescricao, MedicamentoDTO> implements MedicamentoSobPrescricaoCustomRepository {
    @Override
    protected void filterAtributesFromEntity(MedicamentoDTO paramDTO, CriteriaBuilder cb, Root<MedicamentoSobPrescricao> root, List<Predicate> predicates, CriteriaQuery<MedicamentoSobPrescricao> query) {
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
            if (Objects.nonNull(paramDTO.getMarca())) {
                predicates.add(cb.like(cb.lower(root.get("marca")), "%" + paramDTO.getMarca().toLowerCase() + "%"));
            }
            if (Objects.nonNull(paramDTO.isTermolabel())) {
                predicates.add(cb.equal(root.get("termolabel"), paramDTO.isTermolabel()));
            }
        }
    }

    @Override
    public List<MedicamentoSobPrescricao> findAllWithCriteria(MedicamentoDTO dto) {
        return super.findAllWithCriteriaParent(dto);
    }
}
