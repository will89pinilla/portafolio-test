package com.zemoga.portfolio.builder;

import com.zemoga.portfolio.domain.Portafolio;
import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;
import com.zemoga.portfolio.dto.TwtTweet;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestBuilder {
    public static final Integer MAX_TWEETS = 5;
    static final Long PORTFOLIO_ID = 1L;
    static final String DESCRIPTION = "Description";
    static final String URL = "https://image.png";
    static final String NAME = "Name";
    static final String TITLE = "Singer";
    static final String TEXT = "Tweet message";
    static final String NEW_VALUE = "New Title";


    public static final Portafolio PORTAFOLIO = Portafolio.builder()
            .id(PORTFOLIO_ID)
            .description(DESCRIPTION)
            .imageUrl(URL)
            .twitterUserName(NAME)
            .title(TITLE)
            .build();


    static final TwitterProfile  TWITTER_PROFILE = new TwitterProfile(PORTFOLIO_ID, NAME, null, null,
            null, null, null , null);

    public static List<Portafolio> buildPortafolios(){
        return List.of(PORTAFOLIO, Portafolio.builder().build());
    }

    public static List<Portfolio> buildPortfolios(){
        return List.of(buildUpdateObj());
    }

    public static List<Tweet> buildTweets(){
        return IntStream.iterate(0, i -> i + 1)
                .mapToObj(TestBuilder::buildTweet)
                .limit(5)
                .collect(Collectors.toList());
    }

    public static List<TwtTweet> buildListTwtTweets() {
        return IntStream.iterate(0, i -> i + 1)
                .mapToObj(TestBuilder::buildTwtTweets)
                .limit(5)
                .collect(Collectors.toList());
    }

    private static TwtTweet buildTwtTweets(int i) {
        return TwtTweet.builder()
                .text(TEXT.concat(String.valueOf(i)))
                .createdAt(new Date())
                .build();

    }



    private static Tweet buildTweet(int i) {
        Tweet tweet = new Tweet(i, TEXT.concat(String.valueOf(i)), convert(LocalDateTime.now().plusSeconds(1)), URL, null
                ,  null, PORTFOLIO_ID, null,null);
        tweet.setUser(TWITTER_PROFILE);
        return tweet;
    }

    public static Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convert(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Tweet compareDateTweets(Tweet t1, Tweet t2) {
        return convert(t1.getCreatedAt()).isAfter(convert(t2.getCreatedAt())) ? t1 : t2;
    }

    public static Portafolio buildForUpdateObject(){
        return Portafolio
                .builder()
                .title("New Title")
                .twitterUserName(PORTAFOLIO.getTwitterUserName())
                .id(PORTAFOLIO.getId())
                .description(PORTAFOLIO.getDescription())
                .imageUrl(PORTAFOLIO.getImageUrl())
                .build();

    }

    public static Portfolio buildUpdateObj(){
        return Portfolio
                .builder()
                .title(NEW_VALUE)
                .twitterUserName(PORTAFOLIO.getTwitterUserName())
                .id(PORTAFOLIO.getId())
                .description(PORTAFOLIO.getDescription())
                .imageUrl(PORTAFOLIO.getImageUrl())
                .build();
    }

    public static Profile buildProfile(){
        return Profile.builder()
                .portfolio(buildUpdateObj())
                .twtTweets(buildListTwtTweets())
                .build();
    }




}
