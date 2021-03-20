package com.zemoga.portfolio.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Profile {
    private final Portfolio portfolio;
    private final List<TwtTweet> twtTweets;
}
