package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.TwitRepository;

import javax.persistence.EntityManagerFactory;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, Long>
        implements CommentRepository {

    public CommentRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<Comment> getModelClass() {
        return Comment.class;
    }
}
