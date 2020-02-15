package com.joao.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cargo.
 */
@Entity
@Table(name = "cargo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario_cargo", precision = 21, scale = 2)
    private BigDecimal salarioCargo;

    @OneToMany(mappedBy = "cargo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Funcionario> funcionarios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Cargo nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalarioCargo() {
        return salarioCargo;
    }

    public Cargo salarioCargo(BigDecimal salarioCargo) {
        this.salarioCargo = salarioCargo;
        return this;
    }

    public void setSalarioCargo(BigDecimal salarioCargo) {
        this.salarioCargo = salarioCargo;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Cargo funcionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
        return this;
    }

    public Cargo addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
        funcionario.setCargo(this);
        return this;
    }

    public Cargo removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
        funcionario.setCargo(null);
        return this;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cargo)) {
            return false;
        }
        return id != null && id.equals(((Cargo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cargo{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", salarioCargo=" + getSalarioCargo() +
            "}";
    }
}
