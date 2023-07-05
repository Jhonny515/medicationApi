package com.jhonny.medicationApi.repositories.repositories;

import com.jhonny.medicationApi.domain.models.ItensCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinho, Long> {
}
