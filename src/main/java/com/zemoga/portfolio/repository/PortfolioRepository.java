package com.zemoga.portfolio.repository;

import com.zemoga.portfolio.domain.Portfolios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolios, Long> {}