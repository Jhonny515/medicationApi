package com.jhonny.medicationApi.infra.repositories;

import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoSobPrescricaoRepository extends JpaRepository<MedicamentoSobPrescricao, Long> {
}
