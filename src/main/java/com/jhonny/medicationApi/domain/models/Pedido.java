package com.jhonny.medicationApi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Pedido {

    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long id_cliente;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private StatusPedido status;

    @ManyToMany
    @JoinTable(name = "tb_itens_carrinho",
            joinColumns = {@JoinColumn(name = "id_pedido")},
            inverseJoinColumns = {@JoinColumn(name = "id_medicamento")})
    private List<Medicamento> medicamentos;
}
