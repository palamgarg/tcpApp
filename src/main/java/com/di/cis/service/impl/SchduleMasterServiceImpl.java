package com.di.cis.service.impl;

import com.di.cis.domain.SchduleMaster;
import com.di.cis.repository.SchduleMasterRepository;
import com.di.cis.service.SchduleMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.di.cis.domain.SchduleMaster}.
 */
@Service
@Transactional
public class SchduleMasterServiceImpl implements SchduleMasterService {

    private final Logger log = LoggerFactory.getLogger(SchduleMasterServiceImpl.class);

    private final SchduleMasterRepository schduleMasterRepository;

    public SchduleMasterServiceImpl(SchduleMasterRepository schduleMasterRepository) {
        this.schduleMasterRepository = schduleMasterRepository;
    }

    @Override
    public Mono<SchduleMaster> save(SchduleMaster schduleMaster) {
        log.debug("Request to save SchduleMaster : {}", schduleMaster);
        return schduleMasterRepository.save(schduleMaster);
    }

    @Override
    public Mono<SchduleMaster> update(SchduleMaster schduleMaster) {
        log.debug("Request to update SchduleMaster : {}", schduleMaster);
        return schduleMasterRepository.save(schduleMaster);
    }

    @Override
    public Mono<SchduleMaster> partialUpdate(SchduleMaster schduleMaster) {
        log.debug("Request to partially update SchduleMaster : {}", schduleMaster);

        return schduleMasterRepository
            .findById(schduleMaster.getId())
            .map(existingSchduleMaster -> {
                if (schduleMaster.getInstallmentNumber() != null) {
                    existingSchduleMaster.setInstallmentNumber(schduleMaster.getInstallmentNumber());
                }
                if (schduleMaster.getReducingBalance() != null) {
                    existingSchduleMaster.setReducingBalance(schduleMaster.getReducingBalance());
                }
                if (schduleMaster.getPrincipleAmount() != null) {
                    existingSchduleMaster.setPrincipleAmount(schduleMaster.getPrincipleAmount());
                }
                if (schduleMaster.getInterest() != null) {
                    existingSchduleMaster.setInterest(schduleMaster.getInterest());
                }
                if (schduleMaster.getTotalInstallment() != null) {
                    existingSchduleMaster.setTotalInstallment(schduleMaster.getTotalInstallment());
                }
                if (schduleMaster.getDueDate() != null) {
                    existingSchduleMaster.setDueDate(schduleMaster.getDueDate());
                }
                if (schduleMaster.getRemarks() != null) {
                    existingSchduleMaster.setRemarks(schduleMaster.getRemarks());
                }

                return existingSchduleMaster;
            })
            .flatMap(schduleMasterRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<SchduleMaster> findAll() {
        log.debug("Request to get all SchduleMasters");
        return schduleMasterRepository.findAll();
    }

    public Mono<Long> countAll() {
        return schduleMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<SchduleMaster> findOne(Long id) {
        log.debug("Request to get SchduleMaster : {}", id);
        return schduleMasterRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete SchduleMaster : {}", id);
        return schduleMasterRepository.deleteById(id);
    }
}
