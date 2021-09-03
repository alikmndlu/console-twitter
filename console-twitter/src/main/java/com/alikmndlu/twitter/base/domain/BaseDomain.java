package com.alikmndlu.twitter.base.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseDomain<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;
}


