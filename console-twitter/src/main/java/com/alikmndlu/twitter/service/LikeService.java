package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

import java.util.Optional;

public interface LikeService extends BaseService<Like, Long> {

    void viewLikedTwits();

    void unlikeTwit();

    boolean isUserLikedTwit(User loggedInUser, Twit twit);

    void unlike(Twit twit, User user);

    Optional<Like> findUserLikedTwit(Twit twit, User user);

    void like(Twit twit, User user);
}
