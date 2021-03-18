package com.zemoga.portfolio.advice;

import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
class PortfolioNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PortfolioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(PortfolioNotFoundException ex) {
        return ex.getMessage();
    }
}
