package com.di.cis.web.rest;

import com.di.cis.domain.SchduleMaster;
import com.di.cis.repository.SchduleMasterRepository;
import com.di.cis.service.SchduleMasterService;
import com.di.cis.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.di.cis.domain.SchduleMaster}.
 */
@RestController
@RequestMapping("/api/schdule-masters")
public class SchduleMasterResource {

    private final Logger log = LoggerFactory.getLogger(SchduleMasterResource.class);

    private static final String ENTITY_NAME = "schduleMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SchduleMasterService schduleMasterService;

    private final SchduleMasterRepository schduleMasterRepository;

    public SchduleMasterResource(SchduleMasterService schduleMasterService, SchduleMasterRepository schduleMasterRepository) {
        this.schduleMasterService = schduleMasterService;
        this.schduleMasterRepository = schduleMasterRepository;
    }

    /**
     * {@code POST  /schdule-masters} : Create a new schduleMaster.
     *
     * @param schduleMaster the schduleMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new schduleMaster, or with status {@code 400 (Bad Request)} if the schduleMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<SchduleMaster>> createSchduleMaster(@RequestBody SchduleMaster schduleMaster) throws URISyntaxException {
        log.debug("REST request to save SchduleMaster : {}", schduleMaster);
        if (schduleMaster.getId() != null) {
            throw new BadRequestAlertException("A new schduleMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return schduleMasterService
            .save(schduleMaster)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/schdule-masters/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /schdule-masters/:id} : Updates an existing schduleMaster.
     *
     * @param id the id of the schduleMaster to save.
     * @param schduleMaster the schduleMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated schduleMaster,
     * or with status {@code 400 (Bad Request)} if the schduleMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the schduleMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SchduleMaster>> updateSchduleMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SchduleMaster schduleMaster
    ) throws URISyntaxException {
        log.debug("REST request to update SchduleMaster : {}, {}", id, schduleMaster);
        if (schduleMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, schduleMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return schduleMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return schduleMasterService
                    .update(schduleMaster)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /schdule-masters/:id} : Partial updates given fields of an existing schduleMaster, field will ignore if it is null
     *
     * @param id the id of the schduleMaster to save.
     * @param schduleMaster the schduleMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated schduleMaster,
     * or with status {@code 400 (Bad Request)} if the schduleMaster is not valid,
     * or with status {@code 404 (Not Found)} if the schduleMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the schduleMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SchduleMaster>> partialUpdateSchduleMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SchduleMaster schduleMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update SchduleMaster partially : {}, {}", id, schduleMaster);
        if (schduleMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, schduleMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return schduleMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SchduleMaster> result = schduleMasterService.partialUpdate(schduleMaster);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /schdule-masters} : get all the schduleMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schduleMasters in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<SchduleMaster>> getAllSchduleMasters() {
        log.debug("REST request to get all SchduleMasters");
        return schduleMasterService.findAll().collectList();
    }

    /**
     * {@code GET  /schdule-masters} : get all the schduleMasters as a stream.
     * @return the {@link Flux} of schduleMasters.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<SchduleMaster> getAllSchduleMastersAsStream() {
        log.debug("REST request to get all SchduleMasters as a stream");
        return schduleMasterService.findAll();
    }

    /**
     * {@code GET  /schdule-masters/:id} : get the "id" schduleMaster.
     *
     * @param id the id of the schduleMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the schduleMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<SchduleMaster>> getSchduleMaster(@PathVariable Long id) {
        log.debug("REST request to get SchduleMaster : {}", id);
        Mono<SchduleMaster> schduleMaster = schduleMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(schduleMaster);
    }

    /**
     * {@code DELETE  /schdule-masters/:id} : delete the "id" schduleMaster.
     *
     * @param id the id of the schduleMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSchduleMaster(@PathVariable Long id) {
        log.debug("REST request to delete SchduleMaster : {}", id);
        return schduleMasterService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
