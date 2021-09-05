package com.alikmndlu.twitter.util;

import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;

public class DataInitializer {
    public void init(){
        // users
        ApplicationContext.userService.saveOrUpdate(
                new User("ali", "kmndlu", "a", "a")
        );
        ApplicationContext.userService.saveOrUpdate(
                new User("morteza", "ahangari", "b", "b")
        );
        ApplicationContext.userService.saveOrUpdate(
                new User("amirmohamamd", "khanalipoor", "c", "c")
        );

        // twit
        ApplicationContext.twitService.saveOrUpdate(
                new Twit(
                        "ali kmndlu's 1st twit",
                        ApplicationContext.userService.findByUsername("a").get()
                )
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApplicationContext.twitService.saveOrUpdate(
                new Twit(
                        "ali kmndlu's 2nd twit",
                        ApplicationContext.userService.findByUsername("a").get()
                )
        );

        // comment
        ApplicationContext.commentService.saveOrUpdate(
                new Comment(
                        "nice twit",
                        ApplicationContext.twitService.findById(4L).get(),
                        ApplicationContext.userService.findByUsername("b").get()
                )
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApplicationContext.commentService.saveOrUpdate(
                new Comment(
                        "nice twit",
                        ApplicationContext.twitService.findById(4L).get(),
                        ApplicationContext.userService.findByUsername("c").get()
                )
        );

        // likes
        ApplicationContext.likeService.saveOrUpdate(
                new Like(
                        ApplicationContext.userService.findByUsername("b").get(),
                        ApplicationContext.twitService.findById(4L).get()
                )
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApplicationContext.likeService.saveOrUpdate(
                new Like(
                        ApplicationContext.userService.findByUsername("c").get(),
                        ApplicationContext.twitService.findById(4L).get()
                )
        );
    }
}
