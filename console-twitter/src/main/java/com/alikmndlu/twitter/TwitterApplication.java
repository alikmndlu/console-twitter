package com.alikmndlu.twitter;

import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.util.ApplicationContext;
import com.github.javafaker.App;

import java.util.Collection;

public class TwitterApplication {
    public static void main(String[] args) {
        ApplicationContext.userService.findAll();
    }
}
