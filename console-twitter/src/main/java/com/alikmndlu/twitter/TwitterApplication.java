package com.alikmndlu.twitter;

import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.util.Collection;

public class TwitterApplication {
    public static void main(String[] args) {
        User user = ApplicationContext.userService.saveOrUpdate(new User(
                "Ali",
                "Kmndlu",
                "ali_kmndlu",
                "AliKu@8025"
        ));

        Collection<User> all = ApplicationContext.userService.findAll();
        all.forEach(System.out::println);
        System.out.println(all.size());

        ApplicationContext.userService.physicalDelete(user);

        all = ApplicationContext.userService.findAll();

        all.forEach(System.out::println);
        System.out.println(all.size());
    }
}
