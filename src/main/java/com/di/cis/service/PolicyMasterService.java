package com.di.cis.service;

import com.di.cis.domain.PolicyMaster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.di.cis.domain.PolicyMaster}.
 */
public interface PolicyMasterService {
    /**
     * Save a policyMaster.
     *
     * @param policyMaster the entity to save.
     * @return the persisted entity.
     */
    Mono<PolicyMaster> save(PolicyMaster policyMaster);

    /**
     * Updates a policyMaster.
     *
     * @param policyMaster the entity to update.
     * @return the persisted entity.
     */
    Mono<PolicyMaster> update(PolicyMaster policyMaster);

    /**
     * Partially updates a policyMaster.
     *
     * @param policyMaster the entity to update partially.
     * @return the persisted entity.
     */
    Mono<PolicyMaster> partialUpdate(PolicyMaster policyMaster);

    /**
     * Get all the policyMasters.
     *
     * @return the list of entities.
     */
    Flux<PolicyMaster> findAll();

    /**
     * Returns the number of policyMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" policyMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<PolicyMaster> findOne(Long id);

    /**
     * Delete the "id" policyMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
