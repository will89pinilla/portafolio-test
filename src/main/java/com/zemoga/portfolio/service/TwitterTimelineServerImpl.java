package com.zemoga.portfolio.service;

import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TwitterTimelineServerImpl implements TwitterTimelineService {

    private final Twitter twitter;

    TwitterTimelineServerImpl(final Twitter twitter) {
        this.twitter = twitter;
    }

    @Override
    public List<Tweet> pullTweetsByUser(String screenName) {
        return twitter.timelineOperations().getUserTimeline(screenName);
    }
}