package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.UserRepository;

import javax.persistence.EntityManagerFactory;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long>
        implements UserRepository {

    public UserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<User> getModelClass() {
        return User.class;
    }
}
