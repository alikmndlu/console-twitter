package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment extends BaseDomain<Long> {

    private String text;

    @ManyToOne
    private Twit twit;

    @ManyToOne
    private User user;
}
