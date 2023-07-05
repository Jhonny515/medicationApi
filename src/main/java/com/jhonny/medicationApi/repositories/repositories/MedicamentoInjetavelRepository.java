package com.jhonny.medicationApi.repositories.repositories;

import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoInjetavelRepository extends JpaRepository<MedicamentoInjetavel, Long> {
}
