package com.zemoga.portfolio.service;

import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import java.util.List;

import static com.zemoga.portfolio.builder.TestBuilder.PORTAFOLIO;
import static com.zemoga.portfolio.builder.TestBuilder.buildTweets;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TwitterTimelineServerImplTest {

    private TwitterTimelineService twitterTimelineService;

    @Mock
    private Twitter twitter;

    @Mock
    private TimelineOperations timelineOperations;

    @BeforeEach
    void setUp(){
        twitterTimelineService = new TwitterTimelineServerImpl(twitter);
    }

    @Test
    void pullTweetsByUser(){
        List<Tweet> tweets = buildTweets();
        //GIVEN
        given(twitter.timelineOperations()).willReturn(timelineOperations);
        given(twitter.timelineOperations().getUserTimeline(Mockito.any(String.class)))
                .willReturn(tweets);

        //WHEN
        List<Tweet> tweetsResponse = twitterTimelineService
                .pullTweetsByUser(PORTAFOLIO.getTwitterUserName());

        //THEN
        assertEquals(tweetsResponse.size(), tweets.size());
        //Check one Tweet
        Tweet expectedTweet = tweets.get(0);
        Tweet responseTweet = tweetsResponse.get(0);

        assertAll("Tweets",
                () ->  assertEquals(expectedTweet.getCreatedAt(), responseTweet.getCreatedAt()),
                () -> assertEquals(expectedTweet.getUser().getScreenName(), responseTweet.getUser().getScreenName()),
                () -> assertEquals(expectedTweet.getText(), responseTweet.getText())
        );
        verify(twitter, times(2)).timelineOperations();
        verify(timelineOperations).getUserTimeline(Mockito.any(String.class));

    }

    @Test
    void whenItReturnsUnauthorizedException(){

        //GIVEN
        given(twitter.timelineOperations()).willReturn(timelineOperations);
        given(twitter.timelineOperations().getUserTimeline(Mockito.any(String.class)))
                .willThrow(NotAuthorizedException.class);

        //WHEN
        List<Tweet> tweets = twitterTimelineService.pullTweetsByUser(PORTAFOLIO.getTwitterUserName());

        //THEN
        assertEquals(tweets.size(), 0);
        verify(twitter, times(2)).timelineOperations();
        verify(timelineOperations).getUserTimeline(Mockito.any(String.class));

    }

}