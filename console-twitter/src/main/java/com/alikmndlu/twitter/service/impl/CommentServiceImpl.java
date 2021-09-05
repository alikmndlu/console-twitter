package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.CommentRepository;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.CommentService;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public void viewUserComments() {
        List<Comment> comments = new ArrayList<>(Authenticate.getLoggedInUser().getComments());

        if (comments.size() == 0){
            System.out.println("\nThere Is No Comment Yet!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }

        System.out.println();
        int ic = 1;
        for (Comment comment : comments){
            System.out.println(ic + ".  " + comment.getText());
            System.out.println("\tTwit : " + comment.getTwit().getText() + " by {Username:" + comment.getTwit().getUser().getUsername() + ",Name:" + comment.getTwit().getUser().getFirstName() + " " + comment.getTwit().getUser().getLastName() + "}");
            if (ic != comments.size()) System.out.println();
            ic++;
        }
    }

    @Override
    public void editUserComments() {
        List<Comment> comments = new ArrayList<>(Authenticate.getLoggedInUser().getComments());

        if (comments.size() == 0) {
            System.out.println("\nThere Is No Comment Yet!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        System.out.println();
        int ic = 1;
        for (Comment comment : comments) {
            System.out.println(ic + ".  " + comment.getText() + " -> by {Username:" + comment.getTwit().getUser().getUsername() + ", Name:" + comment.getTwit().getUser().getFirstName() + " " + comment.getTwit().getUser().getLastName() + "}");
            if (ic != comments.size()) System.out.println();
            ic++;
        }

        System.out.println("\nEnter -1 For Quit From 'Edit Comment' Section.");
        System.out.println("Enter Comment Index To Edit : ");
        int selectedIndex = ApplicationContext.helper.readInteger("-> ");
        if (selectedIndex == -1){
            System.out.println("\nBack To Dashboard...");
            return;
        }
        if (selectedIndex < 1 || selectedIndex > comments.size()) {
            System.out.println("\nInvalid Index!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        Comment comment = comments.get(selectedIndex - 1);
        boolean quit = false;
        while (!quit){
            ApplicationContext.menu.printEditCommentMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
                case 1 -> {
                    editCommentText(comment);
                    quit = true;
                }
                case 2 -> {
                    deleteComment(comment);
                    quit = true;
                }
                case 3 -> quit = true;
                default -> System.out.println("Invalid Command!");
            }
        }
        ApplicationContext.userService.refreshUser();
        System.out.println("\nBack To Dashboard...");
    }

    private void deleteComment(Comment comment) {
        System.out.println("\nAre You Sure (y/n) ?");
        String input = ApplicationContext.scanner.nextLine();
        if (input.equals("n")) return;
        else if (input.equals("y")) {
            physicalDelete(comment);
            System.out.println("\nComment Delete Successfully.");
        }
        else System.out.println("\nInvalid Command!");
    }

    private void editCommentText(Comment comment) {
        System.out.println("\nCurrent Text : " + comment.getText());
        System.out.println("\nEnter -1 For Quit From 'Edit Comment Text' Section.");
        System.out.println("Enter New Text : ");
        String updatedText = ApplicationContext.scanner.nextLine();
        if (updatedText.equals("-1")) return;
        comment.setText(updatedText);
        saveOrUpdate(comment);
        System.out.println("\nComment Text Update Successfully.");
    }
}
