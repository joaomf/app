package com.joao.app.repository;

import com.joao.app.domain.Pedido;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Pedido entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
