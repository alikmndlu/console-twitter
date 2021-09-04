package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.CommentService;
import com.alikmndlu.twitter.service.UserService;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
