package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.TwitService;
import com.alikmndlu.twitter.service.UserService;

public class TwitServiceImpl extends BaseServiceImpl<Twit, Long, TwitRepository>
        implements TwitService {

    public TwitServiceImpl(TwitRepository repository) {
        super(repository);
    }
}
