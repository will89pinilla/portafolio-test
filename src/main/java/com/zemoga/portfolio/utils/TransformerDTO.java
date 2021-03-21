package com.zemoga.portfolio.utils;

import com.zemoga.portfolio.domain.Portafolio;
import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.TwtTweet;
import org.springframework.social.twitter.api.Tweet;

public final class TransformerDTO {

    private TransformerDTO(){}

    public static Portfolio transform(final Portafolio portfolio){
        return portfolio !=  null ? Portfolio.builder()
                .id(portfolio.getId())
                .description(portfolio.getDescription())
                .imageUrl(portfolio.getImageUrl())
                .title(portfolio.getTitle())
                .twitterUserName(portfolio.getTwitterUserName())
                .build() : null;
    }

    public static Portafolio transform(final Portafolio portafolio, final Portfolio portfolio){
        return portfolio !=  null ? Portafolio.builder()
                .id(portafolio.getId())
                .description(portfolio.getDescription())
                .imageUrl(portfolio.getImageUrl())
                .title(portfolio.getTitle())
                .twitterUserName(portfolio.getTwitterUserName())
                .build() : null;
    }

    public static TwtTweet transform(final Tweet tweet){
        return tweet != null ? TwtTweet.builder()
                .text(tweet.getText())
                .createdAt(tweet.getCreatedAt())
                .build() : null;
    }
}