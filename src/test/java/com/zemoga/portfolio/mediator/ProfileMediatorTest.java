package com.zemoga.portfolio.mediator;

import com.zemoga.portfolio.builder.TestBuilder;
import com.zemoga.portfolio.domain.Portafolio;
import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;
import com.zemoga.portfolio.mediator.contract.PortfolioTweetsMediator;
import com.zemoga.portfolio.service.contract.PortfolioService;
import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.social.twitter.api.Tweet;

import java.util.List;

import static com.zemoga.portfolio.builder.TestBuilder.MAX_TWEETS;
import static com.zemoga.portfolio.builder.TestBuilder.PORTAFOLIO;
import static com.zemoga.portfolio.builder.TestBuilder.buildPortafolios;
import static com.zemoga.portfolio.builder.TestBuilder.buildTweets;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProfileMediatorTest {

    private PortfolioTweetsMediator profileMediator;

    @Mock
    private PortfolioService portfolioService;
    @Mock
    private TwitterTimelineService twitterTimelineService;


    @BeforeEach
    void initService() {
        profileMediator = new ProfileMediator(portfolioService,twitterTimelineService, MAX_TWEETS);
    }


    @Test
    void shouldTransformPortfolioEntityToPortfolio() {
        List<Portafolio> portfolios = buildPortafolios();

        //GIVEN
        given(portfolioService.getAllItems()).willReturn(portfolios);

        //WHEN
        List<Portfolio> portfolioList = profileMediator.getAllPortfolio();

        //THEN
        assertEquals(portfolioList.get(0).getId(), portfolios.get(0).getId());
        assertEquals(portfolioList.get(0).getDescription(), portfolios.get(0).getDescription());
        assertEquals(portfolioList.get(0).getTitle(), portfolios.get(0).getTitle());
        assertEquals(portfolioList.get(0).getImageUrl(), portfolios.get(0).getImageUrl());
        assertEquals(portfolioList.get(0).getTwitterUserName(), portfolios.get(0).getTwitterUserName());

        verify(portfolioService).getAllItems();
    }


    @Test
    void shouldBuildProfileThroughPortfolioAndTweetsServices() {

        List<Tweet> tweets = buildTweets();
        //GIVEN
        given(portfolioService.getByUser(any(Long.class)))
                .willReturn(PORTAFOLIO);
        given(twitterTimelineService.pullTweetsByUser(any(String.class)))
                .willReturn(tweets);

        //WHEN
        Profile profile = profileMediator.getProfile(PORTAFOLIO.getId());

        //THEN
        assertEquals(profile.getTwtTweets().size(), MAX_TWEETS);
        assertAll("portfolio",
                () -> assertEquals(PORTAFOLIO.getImageUrl(), profile.getPortfolio().getImageUrl()),
                () -> assertEquals(PORTAFOLIO.getTwitterUserName(), profile.getPortfolio().getTwitterUserName()),
                () -> assertEquals(PORTAFOLIO.getTitle(), profile.getPortfolio().getTitle()),
                () -> assertEquals(PORTAFOLIO.getDescription(), profile.getPortfolio().getDescription()),
                () -> assertEquals(PORTAFOLIO.getId(), profile.getPortfolio().getId())
        );

        assertEquals(profile.getTwtTweets().get(0).getCreatedAt(),
                tweets.stream().reduce(tweets.get(0),TestBuilder::compareDateTweets)
                        .getCreatedAt());

        verify(portfolioService).getByUser(any(Long.class));
        verify(twitterTimelineService).pullTweetsByUser(any(String.class));
    }

    @Test
    void shouldUpdatePortfolio() {

        Portafolio portafolio = TestBuilder.buildForUpdateObject();
        //GIVEN
        given(portfolioService.getByUser(any(Long.class)))
                .willReturn(PORTAFOLIO);
        given(portfolioService.update(any(Portafolio.class))).willReturn(portafolio);

        //WHEN
        Portfolio portfolio = profileMediator.updatePortfolio(portafolio.getId(), TestBuilder.buildUpdateObj());

        //THEN
        assertAll("portfolio",
                () -> assertEquals(portafolio.getImageUrl(), portfolio.getImageUrl()),
                () -> assertEquals(portafolio.getTwitterUserName(), portfolio.getTwitterUserName()),
                () -> assertEquals(portafolio.getTitle(), portfolio.getTitle()),
                () -> assertEquals(portafolio.getDescription(), portfolio.getDescription()),
                () -> assertEquals(portafolio.getId(), portfolio.getId())
        );
        verify(portfolioService).getByUser(any(Long.class));
        verify(portfolioService).update(any(Portafolio.class));
    }
}