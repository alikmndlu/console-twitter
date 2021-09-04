package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long>
        implements UserRepository {

    public UserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<User> getModelClass() {
        return User.class;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(
                    entityManager.createQuery(
                            "from " + getModelClass().getSimpleName() + " where username = :username",
                            getModelClass()
                    ).setParameter("username", username).getSingleResult()
            );
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }
}
