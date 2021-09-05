package com.alikmndlu.twitter.util;

import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.LikeRepository;
import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.repository.impl.CommentRepositoryImpl;
import com.alikmndlu.twitter.repository.impl.LikeRepositoryImpl;
import com.alikmndlu.twitter.repository.impl.TwitRepositoryImpl;
import com.alikmndlu.twitter.repository.impl.UserRepositoryImpl;
import com.alikmndlu.twitter.service.CommentService;
import com.alikmndlu.twitter.service.LikeService;
import com.alikmndlu.twitter.service.TwitService;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.service.impl.CommentServiceImpl;
import com.alikmndlu.twitter.service.impl.LikeServiceImpl;
import com.alikmndlu.twitter.service.impl.TwitServiceImpl;
import com.alikmndlu.twitter.service.impl.UserServiceImpl;

import java.util.Scanner;

public final class ApplicationContext {
    private ApplicationContext() {

    }

    private static final UserRepository userRepository = new UserRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final UserService userService = new UserServiceImpl(userRepository);

    private static final TwitRepository twitRepository = new TwitRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final TwitService twitService = new TwitServiceImpl(twitRepository);

    private static final CommentRepository commentRepository = new CommentRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final CommentService commentService = new CommentServiceImpl(commentRepository);

    private static final LikeRepository likeRepository = new LikeRepositoryImpl(JPAUtil.getEntityManagerFactory());
    public static final LikeService likeService = new LikeServiceImpl(likeRepository);

    public static final Scanner scanner = new Scanner(System.in);
    public static final Helper helper = new Helper();
    public static final Menu menu = new Menu();
    public static final Layer layer = new Layer();
    public static final DataInitializer dataInitializer = new DataInitializer();
}
