package com.alikmndlu.twitter.util;

import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.repository.impl.TwitRepositoryImpl;
import com.alikmndlu.twitter.repository.impl.UserRepositoryImpl;
import com.alikmndlu.twitter.service.TwitService;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.service.impl.TwitServiceImpl;
import com.alikmndlu.twitter.service.impl.UserServiceImpl;

public final class ApplicationContext {
    private ApplicationContext() {

    }

    private static final UserRepository userRepository = new UserRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final UserService userService = new UserServiceImpl(userRepository);

    private static final TwitRepository twitRepository = new TwitRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final TwitService twitService = new TwitServiceImpl(twitRepository);
}
