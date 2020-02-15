package com.joao.app.web.rest;

import com.joao.app.service.FestaService;
import com.joao.app.web.rest.errors.BadRequestAlertException;
import com.joao.app.service.dto.FestaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.joao.app.domain.Festa}.
 */
@RestController
@RequestMapping("/api")
public class FestaResource {

    private final Logger log = LoggerFactory.getLogger(FestaResource.class);

    private static final String ENTITY_NAME = "festa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FestaService festaService;

    public FestaResource(FestaService festaService) {
        this.festaService = festaService;
    }

    /**
     * {@code POST  /festas} : Create a new festa.
     *
     * @param festaDTO the festaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new festaDTO, or with status {@code 400 (Bad Request)} if the festa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/festas")
    public ResponseEntity<FestaDTO> createFesta(@Valid @RequestBody FestaDTO festaDTO) throws URISyntaxException {
        log.debug("REST request to save Festa : {}", festaDTO);
        if (festaDTO.getId() != null) {
            throw new BadRequestAlertException("A new festa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FestaDTO result = festaService.save(festaDTO);
        return ResponseEntity.created(new URI("/api/festas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /festas} : Updates an existing festa.
     *
     * @param festaDTO the festaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated festaDTO,
     * or with status {@code 400 (Bad Request)} if the festaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the festaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/festas")
    public ResponseEntity<FestaDTO> updateFesta(@Valid @RequestBody FestaDTO festaDTO) throws URISyntaxException {
        log.debug("REST request to update Festa : {}", festaDTO);
        if (festaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FestaDTO result = festaService.save(festaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, festaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /festas} : get all the festas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of festas in body.
     */
    @GetMapping("/festas")
    public ResponseEntity<List<FestaDTO>> getAllFestas(Pageable pageable) {
        log.debug("REST request to get a page of Festas");
        Page<FestaDTO> page = festaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /festas/:id} : get the "id" festa.
     *
     * @param id the id of the festaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the festaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/festas/{id}")
    public ResponseEntity<FestaDTO> getFesta(@PathVariable Long id) {
        log.debug("REST request to get Festa : {}", id);
        Optional<FestaDTO> festaDTO = festaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(festaDTO);
    }

    /**
     * {@code DELETE  /festas/:id} : delete the "id" festa.
     *
     * @param id the id of the festaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/festas/{id}")
    public ResponseEntity<Void> deleteFesta(@PathVariable Long id) {
        log.debug("REST request to delete Festa : {}", id);
        festaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
