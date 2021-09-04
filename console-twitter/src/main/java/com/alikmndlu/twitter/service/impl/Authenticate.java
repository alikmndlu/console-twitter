package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.domain.User;

public class Authenticate {
    private static User loggedInUser;

    public static boolean isLoggedIn(){
        return loggedInUser != null;
    }

    public static void loggedOut(){
        if (loggedInUser != null){
            loggedInUser = null;
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        Authenticate.loggedInUser = loggedInUser;
    }
}
