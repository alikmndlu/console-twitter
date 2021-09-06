package com.alikmndlu.twitter.repository.impl;

import com.alikmndlu.twitter.base.repository.impl.BaseRepositoryImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.LikeRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.Optional;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like, Long>
        implements LikeRepository {

    public LikeRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Class<Like> getModelClass() {
        return Like.class;
    }

    @Override
    public Optional<Like> findUserLikedTwit(Twit twit, User user) {
        try {
            return Optional.of(entityManager.createQuery(
                    "from " + getModelClass().getSimpleName() + " where twit_id = :twit and user_id = :user",
                    getModelClass()
            ).setParameter("twit", twit.getId()).setParameter("user", user.getId()).getSingleResult());
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }
}
