package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = User.TABLE_NAME)
@Getter
@Setter
@ToString
public class User extends BaseDomain<Long> {
    public static final String TABLE_NAME = "users";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Column(name = User.FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = User.LAST_NAME, nullable = false)
    private String lastName;

    @Column(name = User.USERNAME, nullable = false, unique = true)
    private String username;

    @Column(name = User.PASSWORD, nullable = false)
    private String password;

    public User() {
    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
