package com.jhonny.medicationApi.builders;

import com.jhonny.medicationApi.domain.models.MedicamentoInjetavel;
import com.jhonny.medicationApi.domain.models.MedicamentoSobPrescricao;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.dtos.MedicamentoDTO;
import com.jhonny.medicationApi.dtos.PedidoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PedidoBuilder {

    public PedidoDTO entityToDto(Pedido entity) {
        List<MedicamentoDTO> medDTOList;
        if (Objects.isNull(entity.getMedicamentos())){ medDTOList = null;}
        else {
            medDTOList = entity.getMedicamentos()
                    .stream().map( (medicamento) ->{
                        if (medicamento instanceof MedicamentoInjetavel) {
                            return new MedicamentoInjetavelBuilder().entityToDto((MedicamentoInjetavel) medicamento);
                        }
                        if (medicamento instanceof MedicamentoSobPrescricao) {
                            return new MedicamentoSobPrescricaoBuilder().entityToDto((MedicamentoSobPrescricao) medicamento);
                        }
                        else {
                            return new MedicamentoBuilder().entityToDto(medicamento);
                        }
                    }).collect(Collectors.toList());
        }


        return PedidoDTO.builder()
                .id(entity.getId())
                .id_cliente(entity.getId_cliente())
                .status(new StatusPedidoBuilder()
                        .entityToDto(entity.getStatus()))
                .medicamentos(medDTOList)
                .build();
    }
}
