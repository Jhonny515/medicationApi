package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springdoc.api.annotations.ParameterObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("medicamentos")
public class MedicamentoController {

    @NonNull
    private MedicamentoService service;

    @Operation(summary = "Returns a list of medicines, filtering and sorting by zero or more criterias.")
    @GetMapping
    public ResponseEntity getWithCriteria(@ParameterObject MedicamentoSearchInputDTO dto) {
          Page<MedicamentoDTO> results = service.getMedicamentosWithCriteria(dto);

          return ResponseEntity.status(HttpStatus.OK).body(results);
    }


    @PostMapping
    public ResponseEntity save(@RequestBody MedicamentoInputDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMedicamento(dto));
    }


    @PutMapping
    public ResponseEntity update(@RequestBody MedicamentoInputDTO dto, @RequestParam Long id) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.updateMedicamento(dto, id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteMedicamento(id));
    }
}
