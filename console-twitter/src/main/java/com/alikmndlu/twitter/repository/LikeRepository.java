package com.alikmndlu.twitter.repository;

import com.alikmndlu.twitter.base.repository.BaseRepository;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

import java.util.Optional;

public interface LikeRepository extends BaseRepository<Like, Long> {
    Optional<Like> findUserLikedTwit(Twit twit, User user);
}
