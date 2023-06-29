package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
}
