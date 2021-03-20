package com.zemoga.portfolio.mediator;

import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;
import com.zemoga.portfolio.dto.TwtTweet;
import com.zemoga.portfolio.mediator.contract.PortfolioTweetsMediator;
import com.zemoga.portfolio.service.contract.PortfolioService;
import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import com.zemoga.portfolio.utils.TransformerDTO;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zemoga.portfolio.utils.TransformerDTO.transform;

@Component
public class ProfileTweetsPortfolioTweetsMediator implements PortfolioTweetsMediator {

    private final PortfolioService portfolioService;
    private final TwitterTimelineService twitterTimelineService;
    private final Integer maxTweetsNumber;

    public ProfileTweetsPortfolioTweetsMediator(PortfolioService portfolioService, TwitterTimelineService twitterTimelineService,
                                                Integer maxTweetsNumber) {
        this.portfolioService = portfolioService;
        this.twitterTimelineService = twitterTimelineService;
        this.maxTweetsNumber = maxTweetsNumber;
    }

    public List<Portfolio> getAllPortfolio(){
        return portfolioService.getAllItems().stream()
                .map(TransformerDTO::transform)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Profile getProfile(final Long id) {

        Portfolio portfolio = transform(portfolioService.getByUser(id));

        return Profile.builder()
                .portfolio(portfolio)
                .twtTweets(getTweets(portfolio.getTwitterUserName()))
                .build();
    }

    private List<TwtTweet> getTweets(String twitterUser) {
        return twitterTimelineService.pullTweetsByUser(twitterUser).stream()
                .sorted(Comparator.comparing(Tweet::getCreatedAt).reversed())
                .map(TransformerDTO::transform)
                .filter(Objects::nonNull)
                .limit(maxTweetsNumber)
                .collect(Collectors.toList());
    }


}
