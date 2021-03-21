package com.zemoga.portfolio.service.contract;

import com.zemoga.portfolio.domain.Portafolio;

import java.util.List;

public interface PortfolioService {
    Portafolio getByUser(final Long id);
    List<Portafolio> getAllItems();
    Portafolio update(final Portafolio portafolio);
}
