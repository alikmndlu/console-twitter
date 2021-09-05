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
            ApplicationContext.menu.printUserDashboardMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> ApplicationContext.userService.editPersonalInformation();
                case 2 -> ApplicationContext.twitService.writeTwit();
                case 3 -> ApplicationContext.twitService.viewTwits();
                case 4 -> ApplicationContext.twitService.editTwits();
                case 5 -> quit = true;
                default -> System.out.println("Invalid Command!");
            }
        }
        Authenticate.loggedOut();
        System.out.println("LoggedOut Successfully.");
    }
}
