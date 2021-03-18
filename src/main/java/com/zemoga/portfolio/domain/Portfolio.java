package com.zemoga.portfolio.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Portfolio {

    @Id
    @GeneratedValue
    @Column(name = "idportfolio")
    private final Long id;

    @Column(name = "description")
    private final String description;

    @Column(name = "image_url")
    private final String imageUrl;

    @Column(name = "twitter_user_name")
    private final String twitterUserName;

    @Column(name = "title")
    private final String title;

    //Empty constructor for JSON / JPA
    public Portfolio() {
        this(null, null, null, null, null);
    }
}
