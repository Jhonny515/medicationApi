package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.ItensCarrinho;
import com.jhonny.medicationApi.domain.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinho, Long> {
}
