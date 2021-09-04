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
}
