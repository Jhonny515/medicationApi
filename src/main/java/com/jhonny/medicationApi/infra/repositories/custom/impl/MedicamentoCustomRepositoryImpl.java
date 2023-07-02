package com.jhonny.medicationApi.infra.repositories.custom.impl;


import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.infra.repositories.CriteriaParent;
import com.jhonny.medicationApi.infra.repositories.custom.MedicamentoCustomRepository;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class MedicamentoCustomRepositoryImpl extends CriteriaParent<Medicamento, MedicamentoDTO> implements MedicamentoCustomRepository {

    @Override
    public List<Medicamento> findAllWithCriteria(MedicamentoDTO dto) {
        return super.findAllWithCriteriaParent(dto);
    }

    @Override
    protected void filterAtributesFromEntity(MedicamentoDTO paramDTO,
                                             CriteriaBuilder cb,
                                             Root<Medicamento> root,
                                             List<Predicate> predicates) {
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
            if (Objects.nonNull(paramDTO.getSob_prescricao())){
                if (Objects.nonNull(paramDTO.getSob_prescricao().isRetencao())){
                    predicates.add(cb.equal(root.get("retencao"), paramDTO.getSob_prescricao().isRetencao()));
                }
                if (Objects.nonNull(paramDTO.getSob_prescricao().getInjetavel()) && Objects.nonNull(paramDTO.getSob_prescricao().getInjetavel().getTipoAplicacao())) {
                    predicates.add(cb.like(cb.lower(root.get("tipo_aplicacao")), "%" + paramDTO.getSob_prescricao().getInjetavel().getTipoAplicacao().toString().toLowerCase() + "%"));
                }
            }
        }
    }
}
