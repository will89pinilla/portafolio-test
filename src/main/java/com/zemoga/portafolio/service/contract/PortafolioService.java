package com.zemoga.portafolio.service.contract;

import com.zemoga.portafolio.domain.Portafolio;

public interface PortafolioService {
    Portafolio getPortafolioByUser(final Long id);
}
