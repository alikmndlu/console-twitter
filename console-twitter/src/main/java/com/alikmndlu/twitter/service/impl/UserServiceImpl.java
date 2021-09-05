package com.alikmndlu.twitter.service.impl;

import com.alikmndlu.twitter.base.service.impl.BaseServiceImpl;
import com.alikmndlu.twitter.domain.User;
import com.alikmndlu.twitter.repository.UserRepository;
import com.alikmndlu.twitter.service.UserService;
import com.alikmndlu.twitter.util.ApplicationContext;

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
        while (!quit){
            ApplicationContext.menu.printEditPersonalInformationMenu();
            int action = ApplicationContext.helper.readInteger("-> ");

            switch (action){
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
