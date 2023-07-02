package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.infra.repositories.custom.MedicamentoCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>, MedicamentoCustomRepository {

}
