package com.di.cis.service.impl;

import com.di.cis.domain.PolicyMaster;
import com.di.cis.repository.PolicyMasterRepository;
import com.di.cis.service.PolicyMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.di.cis.domain.PolicyMaster}.
 */
@Service
@Transactional
public class PolicyMasterServiceImpl implements PolicyMasterService {

    private final Logger log = LoggerFactory.getLogger(PolicyMasterServiceImpl.class);

    private final PolicyMasterRepository policyMasterRepository;

    public PolicyMasterServiceImpl(PolicyMasterRepository policyMasterRepository) {
        this.policyMasterRepository = policyMasterRepository;
    }

    @Override
    public Mono<PolicyMaster> save(PolicyMaster policyMaster) {
        log.debug("Request to save PolicyMaster : {}", policyMaster);
        return policyMasterRepository.save(policyMaster);
    }

    @Override
    public Mono<PolicyMaster> update(PolicyMaster policyMaster) {
        log.debug("Request to update PolicyMaster : {}", policyMaster);
        return policyMasterRepository.save(policyMaster);
    }

    @Override
    public Mono<PolicyMaster> partialUpdate(PolicyMaster policyMaster) {
        log.debug("Request to partially update PolicyMaster : {}", policyMaster);

        return policyMasterRepository
            .findById(policyMaster.getId())
            .map(existingPolicyMaster -> {
                if (policyMaster.getPurpuseName() != null) {
                    existingPolicyMaster.setPurpuseName(policyMaster.getPurpuseName());
                }
                if (policyMaster.getPolicyName() != null) {
                    existingPolicyMaster.setPolicyName(policyMaster.getPolicyName());
                }
                if (policyMaster.getChargesType() != null) {
                    existingPolicyMaster.setChargesType(policyMaster.getChargesType());
                }
                if (policyMaster.getInterestRate() != null) {
                    existingPolicyMaster.setInterestRate(policyMaster.getInterestRate());
                }
                if (policyMaster.getNumberOfInstallments() != null) {
                    existingPolicyMaster.setNumberOfInstallments(policyMaster.getNumberOfInstallments());
                }
                if (policyMaster.getPenaltyRateOfInterest() != null) {
                    existingPolicyMaster.setPenaltyRateOfInterest(policyMaster.getPenaltyRateOfInterest());
                }
                if (policyMaster.getInstallmentDuration() != null) {
                    existingPolicyMaster.setInstallmentDuration(policyMaster.getInstallmentDuration());
                }

                return existingPolicyMaster;
            })
            .flatMap(policyMasterRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<PolicyMaster> findAll() {
        log.debug("Request to get all PolicyMasters");
        return policyMasterRepository.findAll();
    }

    public Mono<Long> countAll() {
        return policyMasterRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<PolicyMaster> findOne(Long id) {
        log.debug("Request to get PolicyMaster : {}", id);
        return policyMasterRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PolicyMaster : {}", id);
        return policyMasterRepository.deleteById(id);
    }
}
