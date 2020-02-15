package com.joao.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.joao.app.domain.Festa} entity.
 */
public class FestaDTO implements Serializable {

    private Long id;

    private String nome;

    @NotNull
    private String descricao;


    private Long tipoFestaId;

    private String tipoFestaNome;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTipoFestaId() {
        return tipoFestaId;
    }

    public void setTipoFestaId(Long tipoFestaId) {
        this.tipoFestaId = tipoFestaId;
    }

    public String getTipoFestaNome() {
        return tipoFestaNome;
    }

    public void setTipoFestaNome(String tipoFestaNome) {
        this.tipoFestaNome = tipoFestaNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FestaDTO festaDTO = (FestaDTO) o;
        if (festaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), festaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FestaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", tipoFestaId=" + getTipoFestaId() +
            ", tipoFestaNome='" + getTipoFestaNome() + "'" +
            "}";
    }
}
