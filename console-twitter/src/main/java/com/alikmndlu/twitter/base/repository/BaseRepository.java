package com.alikmndlu.twitter.base.repository;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.NonNull;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface BaseRepository<E extends BaseDomain<ID>, ID extends Serializable> {

    E saveOrUpdate(E e);

    Set<E> findAll();

    Optional<E> findById(ID id);

    void physicalDelete(@NonNull E e);

    boolean isExistById(@NonNull ID id);

    long countAll();

    EntityManager getEntityManager();
}
