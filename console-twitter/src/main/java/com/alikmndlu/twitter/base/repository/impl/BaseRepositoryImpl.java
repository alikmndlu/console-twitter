package com.alikmndlu.twitter.base.repository.impl;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import com.alikmndlu.twitter.base.repository.BaseRepository;
import lombok.NonNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.*;

public abstract class BaseRepositoryImpl<E extends BaseDomain<ID>, ID extends Serializable>
        implements BaseRepository<E, ID> {

    private final EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public BaseRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public abstract Class<E> getModelClass();

    @Override
    public E saveOrUpdate(E e) {
        if (e.getId() == null) {
            entityManager.persist(e);
        } else {
            e = entityManager.merge(e);
        }
        return e;
    }

    @Override
    public Set<E> findAll() {
        List<E> resultList = entityManager.createQuery("from " + getModelClass().getSimpleName(), getModelClass()).getResultList();
        return new HashSet<>(resultList);
    }

    @Override
    public Optional<E> findById(ID id) {
        if (isExistById(id)){
            return Optional.of(entityManager.find(getModelClass(), id));
        }
        return Optional.empty();
    }

    @Override
    public void physicalDelete(@NonNull E e) {
        e = saveOrUpdate(e);
        entityManager.remove(e);
    }

    @Override
    public boolean isExistById(ID id) {
        return entityManager.createQuery(
                "select count(*) from " + getModelClass().getSimpleName() + " where id = :id",
                Long.class
        ).setParameter("id", id).getSingleResult() == 1;
    }

    @Override
    public long countAll() {
        return entityManager.createQuery("select count(id) from " + getModelClass().getSimpleName(), Long.class).getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
