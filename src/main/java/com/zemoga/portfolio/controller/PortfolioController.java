package com.zemoga.portfolio.controller;

import com.zemoga.portfolio.service.contract.PortfolioService;
import com.zemoga.portfolio.service.contract.TwitterTimelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
class PortfolioController {

    private final PortfolioService portfolioService;
    private final TwitterTimelineService twitterTimelineService;

    PortfolioController(final PortfolioService portfolioService, TwitterTimelineService twitterTimelineService) {
        this.portfolioService = portfolioService;
        this.twitterTimelineService = twitterTimelineService;
    }

    @GetMapping("/portfolios/{id}")
    ResponseEntity<Object> one(@PathVariable final Long id) {
        return ResponseEntity.ok(twitterTimelineService.pullTweetsByUser(portfolioService.getPortfolioByUser(id).getTwitterUserName()));
    }
}