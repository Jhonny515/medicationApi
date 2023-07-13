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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
    public List<PedidoDTO> getPedidosWithCriteria(PedidoSearchInputDTO dto) {
        return pedidoRepository.findAllWithCriteria(dto)
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

    public HttpStatus setItemQtd(Long idPedido, Long idMedicamento, int qnt) {
        ItensCarrinho item = itensCarrinhoRepository.findAll()
                .stream().reduce((medicamento, nextItem) -> {
                    if (Objects.equals(medicamento.getPedido(), idPedido) &&
                    Objects.equals(medicamento.getMedicamento(), idMedicamento)) {
                        return medicamento;
                    } else {
                        return nextItem;
                    }
                }).orElseThrow();
        item.setQnt(qnt);
        itensCarrinhoRepository.save(item);

        return HttpStatus.OK;
    }

    @Override
    public HttpStatus addItemToCart(Long idCliente, Long idMedicamento, int qtd) {

        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                        .id_cliente(idCliente)
                        .id_status(1)
                .build());

        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            if (pedido.getMedicamentos().isEmpty()) {
                pedido.setMedicamentos( new ArrayList<Medicamento>() );
            }
            pedido.getMedicamentos().add(medicamentoRepository.getReferenceById(idMedicamento));
            pedidoRepository.save(pedido);
            this.setItemQtd(pedido.getId(), idMedicamento, qtd);
            return HttpStatus.OK;
        } else if (pedidoList.size() == 0) {
            Pedido pedido = pedidoBuilder.dtoToEntity(this.createPedido(idCliente));
            pedido.setMedicamentos( new ArrayList<Medicamento>() );
            pedido.getMedicamentos().add(medicamentoRepository.getReferenceById(idMedicamento));
            pedidoRepository.save(pedido);
            this.setItemQtd(pedido.getId(), idMedicamento, qtd);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.CONFLICT;
        }
    }

    @Override
    public HttpStatus alterItemQtd(Long idCliente, Long idMedicamento, int qtd) {
        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                .id_cliente(idCliente)
                .id_status(1)
                .build());

        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            this.setItemQtd(pedido.getId(), idMedicamento, qtd);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.CONFLICT;
        }
    }

    @Override
    public HttpStatus deleteItemFromCart(Long idCliente, Long idMedicamento) {
        List<Pedido> pedidoList = pedidoRepository.findAllWithCriteria(PedidoSearchInputDTO.builder()
                .id_cliente(idCliente)
                .id_status(1)
                .build());

        if (pedidoList.size() == 1) {
            Pedido pedido = pedidoList.get(0);
            Iterator<Medicamento> pedidoCart = pedido.getMedicamentos().iterator();
            while (pedidoCart.hasNext()) {
                Medicamento medicamento = pedidoCart.next();
                if (Objects.equals(medicamento.getId(), idMedicamento)) { pedidoCart.remove(); }
            }
            pedidoRepository.save(pedido);
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.CONFLICT;
        }
    }
}