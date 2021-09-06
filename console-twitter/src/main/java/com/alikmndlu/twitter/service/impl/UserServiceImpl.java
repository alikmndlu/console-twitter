package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.Twit;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.util.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public void login() {
        System.out.println("\nEnter Username : ");
        String emailAddress = ApplicationContext.scanner.nextLine();

        System.out.println("\nEnter Password : ");
        String password = ApplicationContext.scanner.nextLine();

        Optional<User> user = findByUsername(emailAddress);

        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            System.out.println("\nAccount Not Found!");
            return;
        }

        User loggedInUser = user.get();
        saveOrUpdate(loggedInUser);
        Authenticate.setLoggedInUser(loggedInUser);
        System.out.println("\nLoggedIn Successfully.");
    }

    @Override
    public void register() {
        User user = new User();

        System.out.println("\nEnter First Name :");
        user.setFirstName(ApplicationContext.scanner.nextLine());

        System.out.println("\nEnter Last Name :");
        user.setLastName(ApplicationContext.scanner.nextLine());

        System.out.println("\nEnter Username : ");
        user.setUsername(ApplicationContext.scanner.nextLine());

        System.out.println("\nEnter Password : ");
        user.setPassword(ApplicationContext.scanner.nextLine());

        saveOrUpdate(user);
        System.out.println("Register Successfully");
    }

    @Override
    public Optional<User> findByUsername(String username) {
        repository.getEntityManager();
        return repository.findByUsername(username);
    }

    @Override
    public void editPersonalInformation() {
        User user = Authenticate.getLoggedInUser();
        boolean quit = false;
        while (!quit) {
            ApplicationContext.menu.printEditPersonalInformationMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action) {
                case 1 -> {
                    editFirstName(user);
                    quit = true;
                }
                case 2 -> {
                    editLastName(user);
                    quit = true;
                }
                case 3 -> {
                    editPassword(user);
                    quit = true;
                }
                case 4 -> quit = true;
                default -> System.out.println("Invalid Command!");
            }
        }
        refreshUser();
        System.out.println("\nBack To Dashboard...");
    }

    @Override
    public void refreshUser() {
        repository.getEntityManager();
        Authenticate.setLoggedInUser(repository.findByUsername(Authenticate.getLoggedInUser().getUsername()).get());
    }

    @Override
    public void deleteAccount() {
        System.out.println("\nAll Of Twits, Comments And Likes Will Be Delete Permanently.");
        System.out.println("Account Won't Come Back Any More.");
        System.out.println("Are You Sure (y/n) ?");
        String input = ApplicationContext.scanner.nextLine();
        if (input.equals("y")) {
            physicalDelete(Authenticate.getLoggedInUser());
            System.out.println("\nAccount Deleted Successfully.");
            Authenticate.loggedOut();
        } else if (input.equals("n")) {
            System.out.println("\nBack To Dashboard...");
        } else {
            System.out.println("\nInvalid Command!");
            System.out.println("\nBack To Dashboard...");
        }
    }

    @Override
    public List<User> searchAndFindByUsername(String username) {
        repository.getEntityManager();
        return repository.searchAndFindByUsername(username);
    }

    @Override
    public void searchUser() {
        System.out.println("\nEnter Username To Search : ");
        String username = ApplicationContext.scanner.nextLine();
        List<User> users = searchAndFindByUsername(username);
        if (users.size() == 0) {
            System.out.println("\nThere IS No User With This Username Or Similar Username!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }

        System.out.println();
        int iu = 1;
        for (User user : users){
            System.out.println(iu + ".  " + user.getUsername() + " [ " + user.getFirstName() + " " + user.getLastName() + " ]");
        }

        Optional<User> user = selectUserInList(users, "View User", "View Details");
        if (user.isEmpty()){
            return;
        }

        if (user.get().getTwits().size() == 0){
            System.out.println("\nThis User Has No Twit Yet!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return;
        }

        System.out.println();
        ApplicationContext.twitService.printTwitsWithoutDetail(new ArrayList<>(user.get().getTwits()));
        Optional<Twit> twit = ApplicationContext.twitService.selectTwitInList(new ArrayList<>(user.get().getTwits()), "Select Twit", "Like And Comment");
        if (twit.isEmpty()){
            return;
        }

        System.out.println();
        ApplicationContext.twitService.printTwitWithDetails(twit.get());
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
        refreshUser();
    }

    private Optional<User> selectUserInList(List<User> users, String section, String action){
        System.out.println("\nEnter -1 For Quit From '" + section + "' Section.");
        System.out.println("Enter User Index To " + action + " : ");
        int selectedIndex = ApplicationContext.helper.readInteger("-> ");
        if (selectedIndex == -1){
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return Optional.empty();
        }
        if (selectedIndex < 1 || selectedIndex > users.size()) {
            System.out.println("\nInvalid Index!");
            ApplicationContext.menu.returnToDashboardAnnouncement();
            return Optional.empty();
        }

        return Optional.of(users.get(selectedIndex - 1));
    }

    private void editPassword(User user) {
        System.out.println("\nCurrent Password : " + user.getPassword());
        System.out.println("\nEnter -1 For Quit From 'Edit Password' Section.");
        System.out.println("Enter New Password : ");
        String updatedPassword = ApplicationContext.scanner.nextLine();
        if (updatedPassword.equals("-1")) return;
        user.setPassword(updatedPassword);
        saveOrUpdate(user);
        System.out.println("\nPassword Update Successfully.");
    }

    private void editLastName(User user) {
        System.out.println("\nCurrent Last Name : " + user.getLastName());
        System.out.println("\nEnter -1 For Quit From 'Edit Last Name' Section.");
        System.out.println("Enter New Last Name : ");
        String updatedLastName = ApplicationContext.scanner.nextLine();
        if (updatedLastName.equals("-1")) return;
        user.setLastName(updatedLastName);
        saveOrUpdate(user);
        System.out.println("\nLast Name Update Successfully.");
    }

    private void editFirstName(User user) {
        System.out.println("\nCurrent First Name : " + user.getFirstName());
        System.out.println("\nEnter -1 For Quit From 'Edit First Name' Section.");
        System.out.println("Enter New First Name : ");
        String updatedFirstName = ApplicationContext.scanner.nextLine();
        if (updatedFirstName.equals("-1")) return;
        user.setFirstName(updatedFirstName);
        saveOrUpdate(user);
        System.out.println("\nFirst Name Update Successfully.");
    }
}
