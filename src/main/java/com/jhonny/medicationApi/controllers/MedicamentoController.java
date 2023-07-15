package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoInputDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.infra.services.MedicamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Medicamento Controller", description = "API to control medication data.")
@RequestMapping("medicamentos")
public class MedicamentoController {

    @NonNull
    private MedicamentoService service;

    @Operation(summary = "Returns a list of medications(Medicamento), filtering and sorting by zero or more criterias.")
    @ApiResponse(responseCode = "200", description = "Query was sucessful", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MedicamentoDTO.class)) })
    @GetMapping
    public ResponseEntity getWithCriteria(@ParameterObject MedicamentoSearchInputDTO dto) {
          Page<MedicamentoDTO> results = service.getMedicamentosWithCriteria(dto);

          return ResponseEntity.status(HttpStatus.OK).body(results);
    }


    @Operation(summary = "Creates a new medication(Medicamento) and returns the medication.")
    @ApiResponse(responseCode = "201", description = "Medicamento was created", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MedicamentoDTO.class)) })
    @PostMapping
    public ResponseEntity save(@RequestBody MedicamentoInputDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveMedicamento(dto));
    }


    @Operation(summary = "Uptades the data of a medication(Medicamento) and returns the medication.")
    @ApiResponse(responseCode = "201", description = "Medicamento was updated", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MedicamentoDTO.class)) })
    @PutMapping
    public ResponseEntity update(@RequestBody MedicamentoInputDTO dto, @RequestParam Long id) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.updateMedicamento(dto, id));
    }


    @Operation(summary = "Deletes a medication(Medicamento) and returns the deleted medication.")
    @ApiResponse(responseCode = "200", description = "Medicamento was deleted", content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MedicamentoDTO.class)) })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteMedicamento(id));
    }
}
