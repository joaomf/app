package com.joao.app.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.joao.app.domain.ItemPedido} entity.
 */
public class ItemPedidoDTO implements Serializable {

    private Long id;

    private String nome;

    private BigDecimal valorItem;


    private Long pedidoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public void setValorItem(BigDecimal valorItem) {
        this.valorItem = valorItem;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemPedidoDTO itemPedidoDTO = (ItemPedidoDTO) o;
        if (itemPedidoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemPedidoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", valorItem=" + getValorItem() +
            ", pedidoId=" + getPedidoId() +
            "}";
    }
}
