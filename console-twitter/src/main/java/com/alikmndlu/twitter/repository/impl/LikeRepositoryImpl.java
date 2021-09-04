package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.LikeRepository;

import javax.persistence.EntityManagerFactory;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like, Long>
        implements LikeRepository {

    public LikeRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<Like> getModelClass() {
        return Like.class;
    }
}
