package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = Twit.TABLE_NAME)
@Getter
@Setter
public class Twit extends BaseDomain<Long> {
    public static final String TABLE_NAME = "twits";
    public static final String TEXT = "text";

    @Column(name = Twit.TEXT, length = 280, nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Twit() {
    }

    public Twit(String text, User user) {
        this.text = text;
        this.user = user;
    }
}
