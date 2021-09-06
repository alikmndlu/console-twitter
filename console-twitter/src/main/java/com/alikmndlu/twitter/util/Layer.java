package com.alikmndlu.twitter.util;

import com.alikmndlu.twitter.service.impl.Authenticate;

public class Layer {
    public void authenticateLayer() {
        boolean quit = false;
        while (!quit){
            if (Authenticate.isLoggedIn()){
                userDashboard();
                continue;
            }

            ApplicationContext.menu.printAuthenticateMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> ApplicationContext.userService.login();
                case 2 -> ApplicationContext.userService.register();
                case 3 -> quit = true;
                default -> System.out.println("invalid command!");
            }
        }
    }

    private void userDashboard() {
        System.out.println("Welcome " + Authenticate.getLoggedInUser().getFirstName() + " " + Authenticate.getLoggedInUser().getLastName());
        boolean quit = false;
        while (!quit){
            if (!Authenticate.isLoggedIn()) break;
            ApplicationContext.menu.printUserDashboardMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> ApplicationContext.twitService.writeTwit();
                case 2 -> ApplicationContext.twitService.viewAllTwits();
                case 3 -> ApplicationContext.userService.searchUser();
                case 4 -> ApplicationContext.twitService.viewTwits();
                case 5 -> ApplicationContext.twitService.editTwits();
                case 6 -> ApplicationContext.commentService.viewUserComments();
                case 7 -> ApplicationContext.commentService.editUserComments();
                case 8 -> ApplicationContext.likeService.viewLikedTwits();
                case 9 -> ApplicationContext.likeService.unlikeTwit();
                case 10 -> ApplicationContext.userService.editPersonalInformation();
                case 11 -> ApplicationContext.userService.deleteAccount();
                case 12 -> {
                    Authenticate.loggedOut();
                    quit = true;
                }
                default -> System.out.println("Invalid Command!");
            }
        }
        System.out.println("\nLoggedOut Successfully.");
    }
}
