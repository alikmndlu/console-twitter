package com.alikmndlu.twitter.service;

import com.alikmndlu.twitter.base.service.BaseService;
import com.alikmndlu.twitter.domain.User;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {

    void login();

    void register();

    Optional<User> findByUsername(String username);

    void editPersonalInformation();

    void refreshUser();
}
