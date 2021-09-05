package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.TwitService;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TwitServiceImpl extends BaseServiceImpl<Twit, Long, TwitRepository>
        implements TwitService {

    public TwitServiceImpl(TwitRepository repository) {
        super(repository);
    }

    @Override
    public void writeTwit() {
        System.out.println("\nMaximum Character : 280");
        System.out.println("Write Twit : ");
        String twitText = ApplicationContext.scanner.nextLine();
        if (twitText.length() > 280) {
            System.out.println("\nExceed Twit Length, Try Again!");
        } else {
            Twit twit = new Twit(twitText, Authenticate.getLoggedInUser());
            saveOrUpdate(twit);
            ApplicationContext.userService.refreshUser();
            System.out.println("\nTwit Added Successfully.");
        }
        System.out.println("\nBack To Dashboard...");
    }

    @Override
    public void viewTwits() {
        List<Twit> twits = new ArrayList<>(Authenticate.getLoggedInUser().getTwits());

        if (twits.size() == 0) {
            System.out.println("\nThere Is No Twit Yet!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        int it = 1;
        for (Twit twit : twits) {
            System.out.println(it + ".  " + twit.getText());

            System.out.println("\n\tLikes Count : " + twit.getLikes().size());
            if (twit.getLikes().size() > 0) {
                int il = 1;
                for (Comment comment : twit.getComments()) {
                    System.out.println("\t\t" + il + ".  " + comment.getUser().getUsername() + " [ " + comment.getUser().getFirstName() + " " + comment.getUser().getLastName() + " ]");
                    il++;
                }
            }

            System.out.println("\n\tComment Count : " + twit.getComments().size());
            if (twit.getComments().size() > 0) {
                int ic = 1;
                for (Comment comment : twit.getComments()) {
                    System.out.println("\t\t" + ic + ".  " + comment.getText() + " ( " + comment.getUser().getUsername() + " [ " + comment.getUser().getFirstName() + " " + comment.getUser().getLastName() + " ] )");
                    ic++;
                }
            }

            if (it != twits.size()) {
                System.out.println("\n\n");
            }

            it++;
        }
    }

    @Override
    public void editTwits() {
        List<Twit> twits = new ArrayList<>(Authenticate.getLoggedInUser().getTwits());

        if (twits.size() == 0) {
            System.out.println("\nThere Is No Twit Yet!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        System.out.println();
        int it = 1;
        for (Twit twit : twits) {
            System.out.println(it + ".  " + twit.getText());
            if (it != twits.size()) System.out.println();
            it++;
        }

        System.out.println("\nEnter -1 For Quit From 'Edit Twit' Section.");
        System.out.println("Enter Twit Index To Edit : ");
        int selectedIndex = ApplicationContext.helper.readInteger("-> ");
        if (selectedIndex == -1){
            System.out.println("\nBack To Dashboard...");
            return;
        }
        if (selectedIndex < 1 || selectedIndex > twits.size()) {
            System.out.println("\nInvalid Index!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        Twit twit = twits.get(selectedIndex - 1);
        boolean quit = false;
        while (!quit){
            ApplicationContext.menu.printEditTwitMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> {
                    editTwit(twit);
                    quit = true;
                }
                case 2 -> {
                    deleteTwit(twit);
                    quit = true;
                }
                case 3 -> quit = true;
                default -> System.out.println("Invalid Command!");
            }
        }
        ApplicationContext.userService.refreshUser();
        System.out.println("\nBack To Dashboard...");
    }

    private void deleteTwit(Twit twit) {
        System.out.println("\nAre You Sure (y/n) ?");
        String input = ApplicationContext.scanner.nextLine();
        if (input.equals("n")) return;
        else if (input.equals("y")) {
            physicalDelete(twit);
            System.out.println("\nTwit Delete Successfully.");
        }
        else System.out.println("Invalid Command!");
    }

    private void editTwit(Twit twit) {
        System.out.println("\nCurrent Text : " + twit.getText());
        System.out.println("\nEnter -1 For Quit From 'Edit Twit Text' Section.");
        System.out.println("Enter New Text : ");
        String updatedText = ApplicationContext.scanner.nextLine();
        if (updatedText.equals("-1")) return;
        twit.setText(updatedText);
        saveOrUpdate(twit);
        System.out.println("\nTwit Text Update Successfully.");
    }
}
