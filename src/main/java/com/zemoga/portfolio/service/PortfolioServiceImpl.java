package com.zemoga.portfolio.service;

import com.zemoga.portfolio.domain.Portfolio;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.repository.PortfolioRepository;
import com.zemoga.portfolio.service.contract.PortfolioService;
import org.springframework.stereotype.Service;

@Service
class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portafolioRepository;

    PortfolioServiceImpl(final PortfolioRepository portafolioRepository){
        this.portafolioRepository = portafolioRepository;
    }

    @Override
    public Portfolio getPortfolioByUser(final Long id) {
        return portafolioRepository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException(id));
    }
}
