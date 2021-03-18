package com.zemoga.portafolio.controller;

import com.zemoga.portafolio.domain.Portafolio;
import com.zemoga.portafolio.service.contract.PortafolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PortafolioController {

    private final PortafolioService portafolioService;

    @Autowired
    public PortafolioController(final PortafolioService portafolioService) {
        this.portafolioService = portafolioService;
    }

    @GetMapping("/portafolios/{id}")
    public Portafolio one(@PathVariable final Long id) {
        return portafolioService.getPortafolioByUser(id);
    }
}