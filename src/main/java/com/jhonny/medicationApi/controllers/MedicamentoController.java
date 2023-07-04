package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.inputs.MedicamentoSearchInputDTO;
import com.jhonny.medicationApi.services.MedicamentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<MedicamentoDTO> getWithCriteria(MedicamentoSearchInputDTO dto) {
        return  service.getMedicamentosWithCriteria(dto);
    }


    @PostMapping
    public MedicamentoDTO save(@RequestBody MedicamentoDTO dto) {
        return service.saveMedicamento(dto);
    }


    @PutMapping
    public MedicamentoDTO update(@RequestBody MedicamentoSearchInputDTO dto) {
        return service.updateMedicamento(dto);
    }


    @DeleteMapping("/{id}")
    public MedicamentoDTO delete(@PathVariable Long id) {
        return service.deleteMedicamento(id);
    }
}
