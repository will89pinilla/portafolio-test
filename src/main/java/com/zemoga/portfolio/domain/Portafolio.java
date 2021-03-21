package com.zemoga.portfolio.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "portfolio")
public final class Portafolio {

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

    //Empty constructor for JPA
    public Portafolio() {
        this(null, null, null, null, null);
    }
}
