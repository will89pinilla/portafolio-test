package com.zemoga.portfolio.service;

import com.zemoga.portfolio.domain.Portfolios;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.repository.PortfolioRepository;
import com.zemoga.portfolio.service.contract.PortfolioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;

    PortfolioServiceImpl(final PortfolioRepository portfolioRepository){
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Portfolios getByUser(final Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException(id));
    }

    @Override
    public List<Portfolios> getAllItems() {
        return portfolioRepository.findAll();
    }
}
