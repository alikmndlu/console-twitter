package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;

import javax.persistence.EntityManagerFactory;

public class TwitRepositoryImpl extends BaseRepositoryImpl<Twit, Long>
        implements TwitRepository {

    public TwitRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<Twit> getModelClass() {
        return Twit.class;
    }
}
