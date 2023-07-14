package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.MedicamentoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @Operation(summary = "Returns a list of medicines, filtering and sorting by zero or more criterias.")
    @GetMapping
    public ResponseEntity<Page<MedicamentoDTO>> getWithCriteria(@ParameterObject MedicamentoSearchInputDTO dto) {
          Page<MedicamentoDTO> results = service.getMedicamentosWithCriteria(dto);

          return ResponseEntity.status(HttpStatus.OK).body(results);
    }


    @PostMapping
    public MedicamentoDTO save(@RequestBody MedicamentoInputDTO dto) {
        return service.saveMedicamento(dto);
    }


    @PutMapping
    public MedicamentoDTO update(@RequestBody MedicamentoInputDTO dto) {
        return service.updateMedicamento(dto);
    }


    @DeleteMapping("/{id}")
    public MedicamentoDTO delete(@PathVariable Long id) {
        return service.deleteMedicamento(id);
    }
}
