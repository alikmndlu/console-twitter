package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Comment.TABLE_NAME)
@Getter @Setter
public class Comment extends BaseDomain<Long> {

    public static final String TABLE_NAME = "comments";
    public static final String TEXT = "text";
    public static final String CREATE_AT = "create_at";
    public static final String TWIT_ID = "twit_id";
    public static final String USER_ID = "user_id";

    @Column(name = Comment.TEXT)
    private String text;

    @ManyToOne
    @JoinColumn(name = Comment.TWIT_ID)
    private Twit twit;

    @ManyToOne
    @JoinColumn(name = Comment.USER_ID)
    private User user;

    @Column(name = Comment.CREATE_AT, nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    public Comment() {}

    public Comment(String text, Twit twit, User user) {
        this.text = text;
        this.twit = twit;
        this.user = user;
    }
}
