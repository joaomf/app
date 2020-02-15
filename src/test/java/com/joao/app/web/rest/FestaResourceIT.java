package com.joao.app.web.rest;

import com.joao.app.AppApp;
import com.joao.app.domain.Festa;
import com.joao.app.domain.TipoFesta;
import com.joao.app.repository.FestaRepository;
import com.joao.app.service.FestaService;
import com.joao.app.service.dto.FestaDTO;
import com.joao.app.service.mapper.FestaMapper;
import com.joao.app.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.joao.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FestaResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
public class FestaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private FestaRepository festaRepository;

    @Autowired
    private FestaMapper festaMapper;

    @Autowired
    private FestaService festaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFestaMockMvc;

    private Festa festa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FestaResource festaResource = new FestaResource(festaService);
        this.restFestaMockMvc = MockMvcBuilders.standaloneSetup(festaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Festa createEntity(EntityManager em) {
        Festa festa = new Festa()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO);
        // Add required entity
        TipoFesta tipoFesta;
        if (TestUtil.findAll(em, TipoFesta.class).isEmpty()) {
            tipoFesta = TipoFestaResourceIT.createEntity(em);
            em.persist(tipoFesta);
            em.flush();
        } else {
            tipoFesta = TestUtil.findAll(em, TipoFesta.class).get(0);
        }
        festa.setTipoFesta(tipoFesta);
        return festa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Festa createUpdatedEntity(EntityManager em) {
        Festa festa = new Festa()
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);
        // Add required entity
        TipoFesta tipoFesta;
        if (TestUtil.findAll(em, TipoFesta.class).isEmpty()) {
            tipoFesta = TipoFestaResourceIT.createUpdatedEntity(em);
            em.persist(tipoFesta);
            em.flush();
        } else {
            tipoFesta = TestUtil.findAll(em, TipoFesta.class).get(0);
        }
        festa.setTipoFesta(tipoFesta);
        return festa;
    }

    @BeforeEach
    public void initTest() {
        festa = createEntity(em);
    }

    @Test
    @Transactional
    public void createFesta() throws Exception {
        int databaseSizeBeforeCreate = festaRepository.findAll().size();

        // Create the Festa
        FestaDTO festaDTO = festaMapper.toDto(festa);
        restFestaMockMvc.perform(post("/api/festas")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(festaDTO)))
            .andExpect(status().isCreated());

        // Validate the Festa in the database
        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeCreate + 1);
        Festa testFesta = festaList.get(festaList.size() - 1);
        assertThat(testFesta.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testFesta.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createFestaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = festaRepository.findAll().size();

        // Create the Festa with an existing ID
        festa.setId(1L);
        FestaDTO festaDTO = festaMapper.toDto(festa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFestaMockMvc.perform(post("/api/festas")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(festaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Festa in the database
        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescricaoIsRequired() throws Exception {
        int databaseSizeBeforeTest = festaRepository.findAll().size();
        // set the field null
        festa.setDescricao(null);

        // Create the Festa, which fails.
        FestaDTO festaDTO = festaMapper.toDto(festa);

        restFestaMockMvc.perform(post("/api/festas")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(festaDTO)))
            .andExpect(status().isBadRequest());

        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFestas() throws Exception {
        // Initialize the database
        festaRepository.saveAndFlush(festa);

        // Get all the festaList
        restFestaMockMvc.perform(get("/api/festas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(festa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getFesta() throws Exception {
        // Initialize the database
        festaRepository.saveAndFlush(festa);

        // Get the festa
        restFestaMockMvc.perform(get("/api/festas/{id}", festa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(festa.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingFesta() throws Exception {
        // Get the festa
        restFestaMockMvc.perform(get("/api/festas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFesta() throws Exception {
        // Initialize the database
        festaRepository.saveAndFlush(festa);

        int databaseSizeBeforeUpdate = festaRepository.findAll().size();

        // Update the festa
        Festa updatedFesta = festaRepository.findById(festa.getId()).get();
        // Disconnect from session so that the updates on updatedFesta are not directly saved in db
        em.detach(updatedFesta);
        updatedFesta
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);
        FestaDTO festaDTO = festaMapper.toDto(updatedFesta);

        restFestaMockMvc.perform(put("/api/festas")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(festaDTO)))
            .andExpect(status().isOk());

        // Validate the Festa in the database
        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeUpdate);
        Festa testFesta = festaList.get(festaList.size() - 1);
        assertThat(testFesta.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testFesta.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingFesta() throws Exception {
        int databaseSizeBeforeUpdate = festaRepository.findAll().size();

        // Create the Festa
        FestaDTO festaDTO = festaMapper.toDto(festa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFestaMockMvc.perform(put("/api/festas")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(festaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Festa in the database
        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFesta() throws Exception {
        // Initialize the database
        festaRepository.saveAndFlush(festa);

        int databaseSizeBeforeDelete = festaRepository.findAll().size();

        // Delete the festa
        restFestaMockMvc.perform(delete("/api/festas/{id}", festa.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Festa> festaList = festaRepository.findAll();
        assertThat(festaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
