package com.alikmndlu.twitter.domain;

import com.alikmndlu.twitter.base.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
@Getter
@Setter
public class Like extends BaseDomain<Long> {
    @ManyToOne
    private User user;

    @ManyToOne
    private Twit twit;
}
