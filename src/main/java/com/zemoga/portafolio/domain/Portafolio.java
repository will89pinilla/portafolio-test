package com.zemoga.portafolio.domain;

import lombok.*;

import javax.persistence.*;

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



    //Empty constructor for JSON / JPA
    public Portafolio() {
        this(null, null, null, null, null);
    }
}
