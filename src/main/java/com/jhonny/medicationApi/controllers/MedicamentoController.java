package com.jhonny.medicationApi.controllers;

import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.services.MedicamentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("medicamentos")
public class MedicamentoController {

    private MedicamentoService service;

    @GetMapping
    public List<Medicamento> getAll() {
        return  service.getAllMedicamentos();
    }

    @GetMapping("/{id}")
    public Medicamento getById(@PathVariable Long id) {
        var medicamento = service.getMedicamentoById(id);
        return medicamento;
    }

    @PostMapping
    public Medicamento save(@RequestBody MedicamentoDTO dto) {
        return service.saveMedicamento(dto);
    }
}
