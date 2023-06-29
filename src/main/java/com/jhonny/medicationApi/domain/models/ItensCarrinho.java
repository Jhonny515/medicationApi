package com.jhonny.medicationApi.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_itens_carrinho")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ItensCarrinho {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int qnt;
}
