package com.di.cis.web.rest;

import com.di.cis.domain.PolicyMaster;
import com.di.cis.repository.PolicyMasterRepository;
import com.di.cis.service.PolicyMasterService;
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
 * REST controller for managing {@link com.di.cis.domain.PolicyMaster}.
 */
@RestController
@RequestMapping("/api/policy-masters")
public class PolicyMasterResource {

    private final Logger log = LoggerFactory.getLogger(PolicyMasterResource.class);

    private static final String ENTITY_NAME = "policyMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PolicyMasterService policyMasterService;

    private final PolicyMasterRepository policyMasterRepository;

    public PolicyMasterResource(PolicyMasterService policyMasterService, PolicyMasterRepository policyMasterRepository) {
        this.policyMasterService = policyMasterService;
        this.policyMasterRepository = policyMasterRepository;
    }

    /**
     * {@code POST  /policy-masters} : Create a new policyMaster.
     *
     * @param policyMaster the policyMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new policyMaster, or with status {@code 400 (Bad Request)} if the policyMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<PolicyMaster>> createPolicyMaster(@RequestBody PolicyMaster policyMaster) throws URISyntaxException {
        log.debug("REST request to save PolicyMaster : {}", policyMaster);
        if (policyMaster.getId() != null) {
            throw new BadRequestAlertException("A new policyMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return policyMasterService
            .save(policyMaster)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/policy-masters/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /policy-masters/:id} : Updates an existing policyMaster.
     *
     * @param id the id of the policyMaster to save.
     * @param policyMaster the policyMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policyMaster,
     * or with status {@code 400 (Bad Request)} if the policyMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the policyMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<PolicyMaster>> updatePolicyMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PolicyMaster policyMaster
    ) throws URISyntaxException {
        log.debug("REST request to update PolicyMaster : {}, {}", id, policyMaster);
        if (policyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, policyMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return policyMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return policyMasterService
                    .update(policyMaster)
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
     * {@code PATCH  /policy-masters/:id} : Partial updates given fields of an existing policyMaster, field will ignore if it is null
     *
     * @param id the id of the policyMaster to save.
     * @param policyMaster the policyMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated policyMaster,
     * or with status {@code 400 (Bad Request)} if the policyMaster is not valid,
     * or with status {@code 404 (Not Found)} if the policyMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the policyMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PolicyMaster>> partialUpdatePolicyMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PolicyMaster policyMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update PolicyMaster partially : {}, {}", id, policyMaster);
        if (policyMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, policyMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return policyMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PolicyMaster> result = policyMasterService.partialUpdate(policyMaster);

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
     * {@code GET  /policy-masters} : get all the policyMasters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of policyMasters in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<PolicyMaster>> getAllPolicyMasters() {
        log.debug("REST request to get all PolicyMasters");
        return policyMasterService.findAll().collectList();
    }

    /**
     * {@code GET  /policy-masters} : get all the policyMasters as a stream.
     * @return the {@link Flux} of policyMasters.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<PolicyMaster> getAllPolicyMastersAsStream() {
        log.debug("REST request to get all PolicyMasters as a stream");
        return policyMasterService.findAll();
    }

    /**
     * {@code GET  /policy-masters/:id} : get the "id" policyMaster.
     *
     * @param id the id of the policyMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the policyMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PolicyMaster>> getPolicyMaster(@PathVariable Long id) {
        log.debug("REST request to get PolicyMaster : {}", id);
        Mono<PolicyMaster> policyMaster = policyMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(policyMaster);
    }

    /**
     * {@code DELETE  /policy-masters/:id} : delete the "id" policyMaster.
     *
     * @param id the id of the policyMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePolicyMaster(@PathVariable Long id) {
        log.debug("REST request to delete PolicyMaster : {}", id);
        return policyMasterService
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
