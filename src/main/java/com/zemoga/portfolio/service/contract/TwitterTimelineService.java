package com.zemoga.portfolio.service.contract;

import org.springframework.social.twitter.api.Tweet;

import java.util.List;

public interface TwitterTimelineService {

    List<Tweet> pullTweetsByUser(final String screenName);

}
