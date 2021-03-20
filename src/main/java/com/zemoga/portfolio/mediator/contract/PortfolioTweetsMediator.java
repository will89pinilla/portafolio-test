package com.zemoga.portfolio.mediator.contract;

import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;

import java.util.List;

public interface PortfolioTweetsMediator {
    List<Portfolio> getAllPortfolio();
    Profile getProfile(final Long id);

}
