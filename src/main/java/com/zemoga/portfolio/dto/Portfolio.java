package com.zemoga.portfolio.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Portfolio {
    private final Long id;
    private final String description;
    private final String imageUrl;
    private final String twitterUserName;
    private final String title;

    // Empty constructor for JSON
    public Portfolio() {
        this(null,null,null,null,null);
    }
}
