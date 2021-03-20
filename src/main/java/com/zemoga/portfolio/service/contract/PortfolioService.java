package com.zemoga.portfolio.service.contract;

import com.zemoga.portfolio.domain.Portfolios;

import java.util.List;

public interface PortfolioService {
    Portfolios getByUser(final Long id);
    List<Portfolios> getAllItems();
}
