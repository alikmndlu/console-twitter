package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.repository.LikeRepository;
import com.alikmndlu.twitter.service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<Like, Long, LikeRepository>
        implements LikeService {

    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }
}
