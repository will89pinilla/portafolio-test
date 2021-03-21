package com.zemoga.portfolio.service;

import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.social.NotAuthorizedException;

@Service
@Slf4j
public class TwitterTimelineServerImpl implements TwitterTimelineService {

    private static final String ERROR_MESSAGE = "The screenName is unauthorized, when it consumes the API!";
    private final Twitter twitter;

    TwitterTimelineServerImpl(final Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public List<Tweet> pullTweetsByUser(String screenName) {
        List<Tweet> tweets = null;
        try {
            tweets = twitter.timelineOperations().getUserTimeline(screenName);
        } catch (NotAuthorizedException e){
            tweets = List.of();
            log.error(ERROR_MESSAGE);
        }
        return tweets;
    }
}