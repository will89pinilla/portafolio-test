package com.zemoga.portfolio.service.contract;

import com.zemoga.portfolio.domain.Portfolio;

public interface PortfolioService {
    Portfolio getPortfolioByUser(final Long id);
}
