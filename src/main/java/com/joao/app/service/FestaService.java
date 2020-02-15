package com.joao.app.service;

import com.joao.app.domain.Festa;
import com.joao.app.repository.FestaRepository;
import com.joao.app.service.dto.FestaDTO;
import com.joao.app.service.mapper.FestaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Festa}.
 */
@Service
@Transactional
public class FestaService {

    private final Logger log = LoggerFactory.getLogger(FestaService.class);

    private final FestaRepository festaRepository;

    private final FestaMapper festaMapper;

    public FestaService(FestaRepository festaRepository, FestaMapper festaMapper) {
        this.festaRepository = festaRepository;
        this.festaMapper = festaMapper;
    }

    /**
     * Save a festa.
     *
     * @param festaDTO the entity to save.
     * @return the persisted entity.
     */
    public FestaDTO save(FestaDTO festaDTO) {
        log.debug("Request to save Festa : {}", festaDTO);
        Festa festa = festaMapper.toEntity(festaDTO);
        festa = festaRepository.save(festa);
        return festaMapper.toDto(festa);
    }

    /**
     * Get all the festas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FestaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Festas");
        return festaRepository.findAll(pageable)
            .map(festaMapper::toDto);
    }

    /**
     * Get one festa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FestaDTO> findOne(Long id) {
        log.debug("Request to get Festa : {}", id);
        return festaRepository.findById(id)
            .map(festaMapper::toDto);
    }

    /**
     * Delete the festa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Festa : {}", id);
        festaRepository.deleteById(id);
    }
}
