package com.alikmndlu.twitter.base.service.impl;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import com.alikmndlu.twitter.base.repository.BaseRepository;
import com.alikmndlu.twitter.base.service.BaseService;
import lombok.NonNull;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public abstract class BaseServiceImpl<E extends BaseDomain<ID>, ID extends Serializable, R extends BaseRepository<E, ID>>
        implements BaseService<E, ID> {

    protected final R repository;


    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E saveOrUpdate(E e) {
        EntityManager entityManager = repository.getEntityManager();
        entityManager.getTransaction().begin();
        e = repository.saveOrUpdate(e);
        entityManager.getTransaction().commit();
        return e;
    }

    @Override
    public Set<E> findAll() {
        repository.getEntityManager();
        return repository.findAll();
    }

    @Override
    public Optional<E> findById(ID id) {
        repository.getEntityManager();
        return repository.findById(id);
    }

    @Override
    public void physicalDelete(@NonNull E e) {
        EntityManager entityManager = repository.getEntityManager();
        Optional<E> resultRow = repository.findById(e.getId());
        if (resultRow.isPresent()){
            e = resultRow.get();
            entityManager.getTransaction().begin();
            repository.physicalDelete(e);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public boolean isExistById(@NonNull ID id) {
        repository.getEntityManager();
        return repository.isExistById(id);
    }

    @Override
    public long countAll() {
        repository.getEntityManager();
        return repository.countAll();
    }
}
