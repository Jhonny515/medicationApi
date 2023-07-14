package com.jhonny.medicationApi.infra.services.impl;

import com.jhonny.medicationApi.domain.builders.PedidoBuilder;
import com.jhonny.medicationApi.domain.models.ItensCarrinho;
import com.jhonny.medicationApi.domain.models.Medicamento;
import com.jhonny.medicationApi.domain.models.Pedido;
import com.jhonny.medicationApi.domain.dtos.PedidoDTO;
import com.jhonny.medicationApi.domain.dtos.inputs.PedidoSearchInputDTO;
import com.jhonny.medicationApi.infra.repositories.ItensCarrinhoRepository;
import com.jhonny.medicationApi.infra.repositories.MedicamentoRepository;
import com.jhonny.medicationApi.infra.repositories.PedidoRepository;
import com.jhonny.medicationApi.infra.repositories.StatusPedidoRepository;
import com.jhonny.medicationApi.infra.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private StatusPedidoRepository statusPedidoRepository;
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    @Autowired
    private ItensCarrinhoRepository itensCarrinhoRepository;
    @Autowired
    private PedidoBuilder pedidoBuilder;

    @Override
    public List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto, Pageable pageable) {
        return pedidoRepository.findAllWithCriteria(dto, pageable)
                .stream().map(pedido -> {
                    List<ItensCarrinho> itensCarrinho = itensCarrinhoRepository.findByPedido(pedido.getId());
                    return pedidoBuilder.entityToDto(pedido, itensCarrinho);
                })
                .collect(Collectors.toList());
    }

    @Override
    public PedidoDTO createPedido(Long idCliente) {
        Pedido newPedido = Pedido.builder()
                .id_cliente(idCliente)
                .status(statusPedidoRepository.getReferenceById(1))
                .build();

        Pedido createdPedido = pedidoRepository.save(newPedido);

        return pedidoBuilder.entityToDto(createdPedido, itensCarrinhoRepository.findByPedido(createdPedido.getId()));
    }

    public void setItemQtd(Long idPedido, Long idMedicamento, int qnt) {
        ItensCarrinho item = itensCarrinhoRepository.findAll()
                .stream().reduce((medicamento, nextItem) -> {
                    if (Objects.equals(medicamento.getPedido(), idPedido) &&
                    Objects.equals(medicamento.getMedicamento(), idMedicamento)) {
                        return medicamento;
                    } else {
                        return nextItem;
                    }
                }).orElseThrow(()->new NoSuchElementException("Medication not found on this cart."));
        item.setQnt(qnt);
        itensCarrinhoRepository.save(item);

    }

    @Override
    public Long addItemToCart(Long idCliente, Long idMedicamento) {
        Pageable pageable = PageRequest.of(0, 2);

        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                        .id_cliente(idCliente)
                        .id_status(1)
                .build(), pageable);

        Medicamento medicamentoToAdd;
        try {
            medicamentoToAdd = medicamentoRepository.getReferenceById(idMedicamento);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Unable to find Medicamento with id " + idMedicamento.toString());
        }

        Long pedidoId = null;
        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            if (pedido.getMedicamentos().isEmpty()) {
                pedido.setMedicamentos( new ArrayList<Medicamento>() );
            }
            if (!pedido.getMedicamentos().isEmpty() && pedido.getMedicamentos().contains(medicamentoToAdd)){
                this.setItemQtd(pedido.getId(), idMedicamento, (+1));
            } else {
                pedido.getMedicamentos().add(medicamentoToAdd);
                pedidoRepository.save(pedido);
                this.setItemQtd(pedido.getId(), idMedicamento, 1);
                pedidoId = pedido.getId();
            }
        } else if (pedidoList.size() == 0) {
            Pedido pedido = pedidoBuilder.dtoToEntity(this.createPedido(idCliente));
            pedido.setMedicamentos( new ArrayList<Medicamento>() );
            pedido.getMedicamentos().add(medicamentoToAdd);
            pedidoRepository.save(pedido);
            this.setItemQtd(pedido.getId(), idMedicamento, 1);
            pedidoId = pedido.getId();
        }
        else {
            throw new RuntimeException("Internal Error");
        }
        return pedidoId;
    }

    public Long alterItemQtd(Long idCliente, Long idMedicamento, int qtd) {
        Pageable pageable = PageRequest.of(0, 2);

        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                .id_cliente(idCliente)
                .id_status(1)
                .build(), pageable);

        Long pedidoId = null;
        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            this.setItemQtd(pedido.getId(), idMedicamento, qtd);
            pedidoId = pedido.getId();
        }
        else {
            throw new RuntimeException("Internal Error");
        }
        return pedidoId;
    }

    @Override
    public Long deleteItemFromCart(Long idCliente, Long idMedicamento) {
        Pageable pageable = PageRequest.of(0, 2);

        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                .id_cliente(idCliente)
                .id_status(1)
                .build(), pageable);

        Long pedidoId;
        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            Iterator<Medicamento> pedidoCart = pedido.getMedicamentos().iterator();
            while (pedidoCart.hasNext()) {
                Medicamento medicamento = pedidoCart.next();
                if (Objects.equals(medicamento.getId(), idMedicamento)) { pedidoCart.remove(); }
            }
            pedidoRepository.save(pedido);
            pedidoId = pedido.getId();
        }
        else {
            throw new RuntimeException("Internal Error");
        }
        return pedidoId;
    }
}
