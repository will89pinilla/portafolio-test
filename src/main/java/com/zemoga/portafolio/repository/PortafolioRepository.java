package com.zemoga.portafolio.repository;


import com.zemoga.portafolio.domain.Portafolio;
import org.springframework.data.repository.CrudRepository;

public interface PortafolioRepository extends CrudRepository<Portafolio, Long> {}