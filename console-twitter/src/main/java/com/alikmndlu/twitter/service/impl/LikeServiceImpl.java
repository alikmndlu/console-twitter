package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Comment;
import com.alikmndlu.twitter.domain.Like;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.LikeRepository;
import com.alikmndlu.twitter.service.LikeService;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class LikeServiceImpl extends BaseServiceImpl<Like, Long, LikeRepository>
        implements LikeService {

    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }

    @Override
    public void viewLikedTwits() {
        List<Like> likedTwits = new ArrayList<>(Authenticate.getLoggedInUser().getLikes());
        likedTwits.sort(Comparator.comparing(Like::getCreateAt).reversed());

        if (likedTwits.size() == 0) {
            System.out.println("\nThere Is No Like Yet!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }

        printUserLikedTwits(likedTwits);
        ApplicationContext.menu.returnToDashboardAnnouncement();
    }

    @Override
    public void unlikeTwit() {
        List<Like> likedTwits = new ArrayList<>(Authenticate.getLoggedInUser().getLikes());
        likedTwits.sort(Comparator.comparing(Like::getCreateAt).reversed());

        if (likedTwits.size() == 0) {
            System.out.println("\nThere Is No Like Yet!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }

        printUserLikedTwits(likedTwits);

        System.out.println("\nEnter -1 For Quit From 'Unlike Twit' Section.");
        System.out.println("Enter Liked Twit Index To Unlike : ");
        int selectedIndex = ApplicationContext.helper.readInteger("-> ");
        if (selectedIndex == -1) {
            System.out.println("\nBack To Dashboard...");
            return;
        }
        if (selectedIndex < 1 || selectedIndex > likedTwits.size()) {
            System.out.println("\nInvalid Index!");
            System.out.println("\nBack To Dashboard...");
            return;
        }

        Like like = likedTwits.get(selectedIndex - 1);
        System.out.println("\nAre You Sure To Unlike (y/n) ?");
        String input = ApplicationContext.scanner.nextLine();
        if (input.equals("n")) {
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }
        else if (input.equals("y")) {
            physicalDelete(like);
            System.out.println("\nTwit Unlike Successfully.");
        }
        else System.out.println("\nInvalid Command!");
        ApplicationContext.userService.refreshUser();
        ApplicationContext.menu.returnToDashboardAnnouncement();
    }

    private void printUserLikedTwits(List<Like> likedTwits) {
        System.out.println();
        int il = 1;
        for (Like like : likedTwits) {
            System.out.println(il + ".  " + like.getTwit().getText() + " -> by {Username:" + like.getTwit().getUser().getUsername() + ", Name:" + like.getTwit().getUser().getFirstName() + " " + like.getTwit().getUser().getLastName() + "}");
            if (il != likedTwits.size()) System.out.println();
            il++;
        }
    }
}
