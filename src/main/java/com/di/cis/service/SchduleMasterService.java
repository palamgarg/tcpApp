package com.di.cis.service;

import com.di.cis.domain.SchduleMaster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for managing {@link com.di.cis.domain.SchduleMaster}.
 */
public interface SchduleMasterService {
    /**
     * Save a schduleMaster.
     *
     * @param schduleMaster the entity to save.
     * @return the persisted entity.
     */
    Mono<SchduleMaster> save(SchduleMaster schduleMaster);

    /**
     * Updates a schduleMaster.
     *
     * @param schduleMaster the entity to update.
     * @return the persisted entity.
     */
    Mono<SchduleMaster> update(SchduleMaster schduleMaster);

    /**
     * Partially updates a schduleMaster.
     *
     * @param schduleMaster the entity to update partially.
     * @return the persisted entity.
     */
    Mono<SchduleMaster> partialUpdate(SchduleMaster schduleMaster);

    /**
     * Get all the schduleMasters.
     *
     * @return the list of entities.
     */
    Flux<SchduleMaster> findAll();

    /**
     * Returns the number of schduleMasters available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" schduleMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<SchduleMaster> findOne(Long id);

    /**
     * Delete the "id" schduleMaster.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(Long id);
}
