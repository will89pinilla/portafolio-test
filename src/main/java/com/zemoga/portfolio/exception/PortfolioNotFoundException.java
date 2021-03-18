package com.zemoga.portfolio.exception;

public class PortfolioNotFoundException extends RuntimeException {
    public PortfolioNotFoundException(Long id) {
        super(String.format("Could not find User %d",id));
    }
}
