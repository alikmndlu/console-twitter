package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Like.TABLE_NAME)
@Getter @Setter
public class Like extends BaseDomain<Long> {

    public static final String TABLE_NAME = "likes";
    public static final String USER_ID = "user_id";
    public static final String TWIT_ID = "twit_id";
    public static final String CREATE_AT = "create_at";

    @ManyToOne
    @JoinColumn(name = Like.USER_ID)
    private User user;

    @ManyToOne
    @JoinColumn(name = Like.TWIT_ID)
    private Twit twit;

    @Column(name = Like.CREATE_AT, nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    public Like() {
    }

    public Like(User user, Twit twit) {
        this.user = user;
        this.twit = twit;
    }
}
