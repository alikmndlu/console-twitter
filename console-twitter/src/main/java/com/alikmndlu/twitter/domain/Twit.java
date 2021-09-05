package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = Twit.TABLE_NAME)
@Getter @Setter
public class Twit extends BaseDomain<Long> {

    public static final String TABLE_NAME = "twits";
    public static final String USER_ID = "user_id";
    public static final String TEXT = "text";
    public static final String CREATE_AT = "create_at";

    @Column(name = Twit.TEXT, length = 280, nullable = false)
    private String text;

    @Column(name = Twit.CREATE_AT, nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = Twit.USER_ID)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "twit")
    private Set<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "twit")
    private Set<Like> likes;

    public Twit() {
    }

    public Twit(String text, User user) {
        this.text = text;
        this.user = user;
    }
}
