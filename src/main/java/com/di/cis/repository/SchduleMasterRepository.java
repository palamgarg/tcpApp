package com.di.cis.repository;

import com.di.cis.domain.SchduleMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SchduleMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchduleMasterRepository extends ReactiveCrudRepository<SchduleMaster, Long>, SchduleMasterRepositoryInternal {
    @Override
    <S extends SchduleMaster> Mono<S> save(S entity);

    @Override
    Flux<SchduleMaster> findAll();

    @Override
    Mono<SchduleMaster> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SchduleMasterRepositoryInternal {
    <S extends SchduleMaster> Mono<S> save(S entity);

    Flux<SchduleMaster> findAllBy(Pageable pageable);

    Flux<SchduleMaster> findAll();

    Mono<SchduleMaster> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SchduleMaster> findAllBy(Pageable pageable, Criteria criteria);
}
