package com.alikmndlu.twitter.repository;

import com.alikmndlu.twitter.base.repository.BaseRepository;
import com.alikmndlu.twitter.domain.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
