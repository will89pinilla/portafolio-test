package com.zemoga.portafolio.domain;

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
public final class Profile {

    @Id
    @Column(name = "idportfolio")
    private Integer id;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "twitter_user_name")
    private String twitterUserName;

    @Column(name = "id_portafolio")
    private Integer idPortafolio;

}
