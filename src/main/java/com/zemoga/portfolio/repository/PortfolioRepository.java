package com.zemoga.portfolio.repository;

import com.zemoga.portfolio.domain.Portafolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portafolio, Long> {}