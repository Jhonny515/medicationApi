package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
