package com.alikmndlu.twitter.util;

import com.alikmndlu.twitter.domain.User;

public class DataInitializer {
    public void init(){
        ApplicationContext.userService.saveOrUpdate(new User("ali", "kmndlu", "a", "a"));
    }
}
