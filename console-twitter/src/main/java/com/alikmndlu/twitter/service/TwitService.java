package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

public interface TwitService extends BaseService<Twit, Long> {

    void writeTwit();

    void viewTwits();

    void editTwits();

    void viewAllTwits();
}
