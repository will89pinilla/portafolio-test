package com.zemoga.portfolio.service;

import com.zemoga.portfolio.domain.Portafolio;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.repository.PortfolioRepository;
import com.zemoga.portfolio.service.contract.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.zemoga.portfolio.builder.TestBuilder.PORTAFOLIO;
import static com.zemoga.portfolio.builder.TestBuilder.buildPortafolios;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {

    private PortfolioService portfolioService;

    @Mock
    private PortfolioRepository portfolioRepository;

    @BeforeEach
    public void setUp(){
        portfolioService = new PortfolioServiceImpl(portfolioRepository);
    }

    @Test
    void getAllItems() {
        List<Portafolio> portfolios = buildPortafolios();

        //GIVEN
        given(portfolioRepository.findAll()).willReturn(portfolios);

        //WHEN
        List<Portafolio> portfoliosList = portfolioService.getAllItems();

        //THEN
        assertEquals(portfoliosList.size(), portfolios.size());
        verify(portfolioRepository).findAll();

    }

    @Test
    void getByUser() {
        //GIVEN
        given(portfolioRepository.findById(any(Long.class)))
                .willReturn(Optional.of(PORTAFOLIO));

        //WHEN
        Portafolio portfolioResponse = portfolioService.getByUser(PORTAFOLIO.getId());

        //THEN
        assertEquals(PORTAFOLIO.getId(), portfolioResponse.getId());
        verify(portfolioRepository).findById(any(Long.class));
    }

    @Test
    void getByUserWhenOptionalIsEmpty() {
        //GIVEN
        given(portfolioRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        //WHEN
        Exception exception = assertThrows(PortfolioNotFoundException.class, () -> {
            portfolioService.getByUser(PORTAFOLIO.getId());
        });

        String expectedMessage = "Could not find User 1";
        String actualMessage = exception.getMessage();

        //THEN
        verify(portfolioRepository).findById(any(Long.class));
        assertTrue(actualMessage.contains(expectedMessage));
    }



    @Test
    void update() {
        //GIVEM
        given(portfolioRepository.save(any(Portafolio.class)))
                .willReturn(PORTAFOLIO);

        //WHEN
        Portafolio portafolio = portfolioService.update(PORTAFOLIO);

        //THEN
        verify(portfolioRepository).save(any(Portafolio.class));
        assertEquals(PORTAFOLIO, portafolio);
    }
}