package com.jhonny.medicationApi.repositories.repositories;

import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.repositories.repositories.custom.PedidoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoCustomRepository {
}
