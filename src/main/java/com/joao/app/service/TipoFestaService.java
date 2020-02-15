package com.joao.app.service;

import com.joao.app.domain.TipoFesta;
import com.joao.app.repository.TipoFestaRepository;
import com.joao.app.service.dto.TipoFestaDTO;
import com.joao.app.service.mapper.TipoFestaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoFesta}.
 */
@Service
@Transactional
public class TipoFestaService {

    private final Logger log = LoggerFactory.getLogger(TipoFestaService.class);

    private final TipoFestaRepository tipoFestaRepository;

    private final TipoFestaMapper tipoFestaMapper;

    public TipoFestaService(TipoFestaRepository tipoFestaRepository, TipoFestaMapper tipoFestaMapper) {
        this.tipoFestaRepository = tipoFestaRepository;
        this.tipoFestaMapper = tipoFestaMapper;
    }

    /**
     * Save a tipoFesta.
     *
     * @param tipoFestaDTO the entity to save.
     * @return the persisted entity.
     */
    public TipoFestaDTO save(TipoFestaDTO tipoFestaDTO) {
        log.debug("Request to save TipoFesta : {}", tipoFestaDTO);
        TipoFesta tipoFesta = tipoFestaMapper.toEntity(tipoFestaDTO);
        tipoFesta = tipoFestaRepository.save(tipoFesta);
        return tipoFestaMapper.toDto(tipoFesta);
    }

    /**
     * Get all the tipoFestas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoFestaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoFestas");
        return tipoFestaRepository.findAll(pageable)
            .map(tipoFestaMapper::toDto);
    }

    /**
     * Get one tipoFesta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TipoFestaDTO> findOne(Long id) {
        log.debug("Request to get TipoFesta : {}", id);
        return tipoFestaRepository.findById(id)
            .map(tipoFestaMapper::toDto);
    }

    /**
     * Delete the tipoFesta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoFesta : {}", id);
        tipoFestaRepository.deleteById(id);
    }
}
