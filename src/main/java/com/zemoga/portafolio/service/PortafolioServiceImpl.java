package com.zemoga.portafolio.service;

import com.zemoga.portafolio.domain.Portafolio;
import com.zemoga.portafolio.repository.PortafolioRepository;
import com.zemoga.portafolio.service.contract.PortafolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortafolioServiceImpl implements PortafolioService {

    private final PortafolioRepository portafolioRepository;

    @Autowired
    PortafolioServiceImpl(final PortafolioRepository portafolioRepository){
        this.portafolioRepository = portafolioRepository;
    }

    @Override
    public Portafolio getPortafolioByUser(final Long id) {
        return portafolioRepository.findById(id).orElseThrow();
    }
}
