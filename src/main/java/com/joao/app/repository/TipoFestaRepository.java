package com.joao.app.repository;

import com.joao.app.domain.TipoFesta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoFesta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoFestaRepository extends JpaRepository<TipoFesta, Long> {

}
