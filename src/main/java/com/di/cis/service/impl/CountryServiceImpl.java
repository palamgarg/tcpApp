package com.di.cis.service.impl;

import com.di.cis.domain.Country;
import com.di.cis.repository.CountryRepository;
import com.di.cis.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.di.cis.domain.Country}.
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Mono<Country> save(Country country) {
        log.debug("Request to save Country : {}", country);
        return countryRepository.save(country);
    }

    @Override
    public Mono<Country> update(Country country) {
        log.debug("Request to update Country : {}", country);
        return countryRepository.save(country);
    }

    @Override
    public Mono<Country> partialUpdate(Country country) {
        log.debug("Request to partially update Country : {}", country);

        return countryRepository
            .findById(country.getId())
            .map(existingCountry -> {
                if (country.getCountryName() != null) {
                    existingCountry.setCountryName(country.getCountryName());
                }

                return existingCountry;
            })
            .flatMap(countryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Country> findAll() {
        log.debug("Request to get all Countries");
        return countryRepository.findAll();
    }

    /**
     *  Get all the countries where Location is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<Country> findAllWhereLocationIsNull() {
        log.debug("Request to get all countries where Location is null");
        return countryRepository.findAllWhereLocationIsNull();
    }

    public Mono<Long> countAll() {
        return countryRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Country> findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        return countryRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Country : {}", id);
        return countryRepository.deleteById(id);
    }
}
