package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;

public interface LikeService extends BaseService<Like, Long> {

    void viewLikedTwits();

    void unlikeTwit();
}
