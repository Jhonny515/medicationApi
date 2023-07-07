package com.jhonny.medicationApi.repositories.repositories.custom.impl;

import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.models.StatusPedido;
import com.jhonny.medicationApi.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.repositories.repositories.CriteriaParent;
import com.jhonny.medicationApi.repositories.repositories.custom.PedidoCustomRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.List;
import java.util.Objects;

public class PedidoCustomRepositoryImpl extends CriteriaParent<Pedido, PedidoSearchInputDTO>
implements PedidoCustomRepository {
    @Override
    public List<Pedido> findAllWithCriteria(PedidoSearchInputDTO dto){
        return super.findAllWithCriteriaParent(dto);
    };

    @Override
    protected void filterAtributesFromEntity(PedidoSearchInputDTO paramDTO, CriteriaBuilder cb, Root<Pedido> root, List<Predicate> predicates, CriteriaQuery<Pedido> query) {
        if (Objects.nonNull(paramDTO)){
            if (Objects.nonNull(paramDTO.getId())) {
                predicates.add(cb.equal(
                        root.get("id"), paramDTO.getId()
                ));
            }
            if (Objects.nonNull(paramDTO.getId_cliente())) {
                predicates.add(cb.equal(root.get("id_cliente"), paramDTO.getId_cliente()));
            }
            // Pesquisar StatusPedido
            root.fetch("status");
            if (Objects.nonNull(paramDTO.getId_status())) {
                predicates.add(cb.equal(
                        root.get("status").get("id"),
                        paramDTO.getId_status()
                ));
            }
            if(Objects.nonNull(paramDTO.getStatus())) {
                predicates.add(cb.like(
                        cb.lower(root.get("status").get("status")),
                        "%" + paramDTO.getStatus() + "%"
                ));
            }
        }
    }
}
