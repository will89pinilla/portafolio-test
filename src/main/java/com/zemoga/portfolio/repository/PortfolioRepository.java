package com.zemoga.portfolio.repository;


import com.zemoga.portfolio.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {}