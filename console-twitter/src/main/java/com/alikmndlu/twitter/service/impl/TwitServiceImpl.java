package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.TwitRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.TwitService;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

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
        List<Twit> twits = getUserTwits(Authenticate.getLoggedInUser());

        if (twits.size() == 0) {
            System.out.println("\nThere Is No Twit Yet!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        System.out.println();
        printTwitsWithDetails(twits);
        ApplicationContext.menu.returnToDashboardAnnouncement();
    }

    @Override
    public void editTwits() {
        List<Twit> twits = getUserTwits(Authenticate.getLoggedInUser());

        if (twits.size() == 0) {
            System.out.println("\nThere Is No Twit Yet!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        System.out.println();
        printTwitsWithoutDetail(twits);

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
        ApplicationContext.menu.returnToDashboardAnnouncement();
    }

    @Override
    public void viewAllTwits() {
        List<Twit> twits = new ArrayList<>(findAll());

        if (twits.size() == 0){
            System.out.println("There Is No Twit Yet!");
            return;
        }

        printTwitsWithoutDetail(twits);
        Optional<Twit> twit = selectTwitInList(twits, "View Twits", "View Details");
        if (twit.isEmpty()){
            return;
        }

        printTwitWithDetails(twit.get());
        boolean isUserLikedThisTwit = ApplicationContext.likeService.isUserLikedTwit(Authenticate.getLoggedInUser(), twit.get());

        boolean quit = false;
        while (!quit){
            ApplicationContext.menu.printViewTwitMenu(isUserLikedThisTwit);
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> {
                    ApplicationContext.commentService.PostComment(twit.get(), Authenticate.getLoggedInUser());
                    quit = true;
                }
                case 2 -> {
                    if (isUserLikedThisTwit){
                        ApplicationContext.likeService.unlike(twit.get(), Authenticate.getLoggedInUser());
                    } else {
                        ApplicationContext.likeService.like(twit.get(), Authenticate.getLoggedInUser());
                    }
                    quit = true;
                }
                case 3 -> quit = true;
                default -> System.out.println("Invalid Command!");
            }
        }
        ApplicationContext.userService.refreshUser();
        ApplicationContext.menu.returnToDashboardAnnouncement();
    }

    private Optional<Twit> selectTwitInList(List<Twit> twits, String section, String action){
        System.out.println("\nEnter -1 For Quit From '" + section + "' Section.");
        System.out.println("Enter Twit Index To " + action + " : ");
        int selectedIndex = ApplicationContext.helper.readInteger("-> ");
        if (selectedIndex == -1){
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return Optional.empty();
        }
        if (selectedIndex < 1 || selectedIndex > twits.size()) {
            System.out.println("\nInvalid Index!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return Optional.empty();
        }

        return Optional.of(twits.get(selectedIndex - 1));
    }

    private void deleteTwit(Twit twit) {
        System.out.println("\nAre You Sure (y/n) ?");
        String input = ApplicationContext.scanner.nextLine();
        if (input.equals("n")) return;
        else if (input.equals("y")) {
            physicalDelete(twit);
            System.out.println("\nTwit Delete Successfully.");
        }
        else System.out.println("\nInvalid Command!");
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

    private List<Twit> getUserTwits(User user){
        List<Twit> twits = new ArrayList<>(user.getTwits());
        twits.sort(Comparator.comparing(Twit::getCreateAt).reversed());
        return twits;
    }

    private void printTwitsWithDetails(List<Twit> twits){
        int it = 1;
        for (Twit twit : twits) {
            System.out.print(it);
            printTwitWithDetails(twit);
            if (it != twits.size()) {
                System.out.println("\n\n");
            }
            it++;
        }
    }

    private void printTwitWithDetails(Twit twit){
        System.out.println(".  " + twit.getText());

        System.out.println("\n\tLikes Count : " + twit.getLikes().size());
        if (twit.getLikes().size() > 0) {
            List<Like> likes = new ArrayList<>(twit.getLikes());
            likes.sort(Comparator.comparing(Like::getCreateAt).reversed());
            int il = 1;
            for (Like like : likes) {
                System.out.println("\t\t" + il + ".  " + like.getUser().getUsername() + " [ " + like.getUser().getFirstName() + " " + like.getUser().getLastName() + " ]");
                il++;
            }
        }

        System.out.println("\n\tComment Count : " + twit.getComments().size());
        if (twit.getComments().size() > 0) {
            List<Comment> comments = new ArrayList<>(twit.getComments());
            comments.sort(Comparator.comparing(Comment::getCreateAt).reversed());
            int ic = 1;
            for (Comment comment : comments) {
                System.out.println("\t\t" + ic + ".  " + comment.getText() + " ( " + comment.getUser().getUsername() + " [ " + comment.getUser().getFirstName() + " " + comment.getUser().getLastName() + " ] )");
                ic++;
            }
        }

        System.out.println("\n\tPosted At : " + twit.getCreateAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

    }

    private void printTwitsWithoutDetail(List<Twit> twits){
        int it = 1;
        for (Twit twit : twits) {
            System.out.println(it + ".  " + twit.getText());
            if (it != twits.size()) System.out.println();
            it++;
        }
    }
}
