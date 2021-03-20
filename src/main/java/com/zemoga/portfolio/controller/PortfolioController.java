package com.zemoga.portfolio.controller;

import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;
import com.zemoga.portfolio.mediator.ProfileTweetsPortfolioTweetsMediator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@CrossOrigin(origins = {"http://localhost:8080/api/portfolios"})
class PortfolioController {

    private final ProfileTweetsPortfolioTweetsMediator portfolioTweetMediator;

    public PortfolioController(final ProfileTweetsPortfolioTweetsMediator portfolioTweetMediator) {
        this.portfolioTweetMediator = portfolioTweetMediator;
    }

    @GetMapping
    ResponseEntity<List<Portfolio>> all() {
        return ResponseEntity.ok(portfolioTweetMediator.getAllPortfolio());
    }

    @GetMapping("/{id}")
    ResponseEntity<Profile> one(@PathVariable final Long id) {
        return ResponseEntity.ok(portfolioTweetMediator.getProfile(id));
    }
}