package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.ItensCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinho, Long> {
    List<ItensCarrinho> findByPedido(Long idPedido);
}
