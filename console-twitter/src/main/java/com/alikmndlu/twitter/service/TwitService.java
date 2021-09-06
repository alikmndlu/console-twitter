package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

import java.util.List;
import java.util.Optional;

public interface TwitService extends BaseService<Twit, Long> {

    void writeTwit();

    void viewTwits();

    void editTwits();

    void viewAllTwits();

    void printTwitsWithoutDetail(List<Twit> twits);

    Optional<Twit> selectTwitInList(List<Twit> twits, String section, String action);

    void printTwitWithDetails(Twit twit);
}
