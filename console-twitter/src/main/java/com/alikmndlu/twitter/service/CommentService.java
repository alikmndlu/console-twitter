package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

public interface CommentService extends BaseService<Comment, Long> {

    void viewUserComments();

    void editUserComments();

    void PostComment(Twit twit, User user);
}
