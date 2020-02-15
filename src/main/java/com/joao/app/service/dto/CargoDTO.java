package com.joao.app.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.joao.app.domain.Cargo} entity.
 */
public class CargoDTO implements Serializable {

    private Long id;

    private String nome;

    private BigDecimal salarioCargo;


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

    public BigDecimal getSalarioCargo() {
        return salarioCargo;
    }

    public void setSalarioCargo(BigDecimal salarioCargo) {
        this.salarioCargo = salarioCargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CargoDTO cargoDTO = (CargoDTO) o;
        if (cargoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cargoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CargoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", salarioCargo=" + getSalarioCargo() +
            "}";
    }
}
