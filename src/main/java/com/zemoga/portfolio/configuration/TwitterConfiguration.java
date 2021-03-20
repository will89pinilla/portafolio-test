package com.zemoga.portfolio.configuration;

import org.hibernate.dialect.Ingres9Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfiguration {

    @Value("${spring.social.twitter.appId}")
    private String consumerKey;

    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;

    @Value("${twitter.access.token}")
    private String accessToken;

    @Value("${twitter.access.token.secret}")
    private String accessTokenSecret;

    @Value("${maxTweetsNumber}")
    private String maxTweetsNumber;

    @Bean
    TwitterTemplate getTwtTemplate(){
        return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
    }

    @Bean
    Integer getMaxTweetsNumber(){
        return Integer.valueOf(maxTweetsNumber);
    }
}
