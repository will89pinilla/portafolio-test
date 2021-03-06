package com.zemoga.portfolio.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class TwtTweet {
    private final String text;
    private final Date createdAt;
}
