package com.zemoga.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.portfolio.builder.TestBuilder;
import com.zemoga.portfolio.dto.Portfolio;
import com.zemoga.portfolio.dto.Profile;
import com.zemoga.portfolio.mediator.ProfileMediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.zemoga.portfolio.builder.TestBuilder.PORTAFOLIO;
import static com.zemoga.portfolio.builder.TestBuilder.buildPortfolios;
import static com.zemoga.portfolio.builder.TestBuilder.buildProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PortfolioController.class)
class PortfolioControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProfileMediator portfolioTweetMediator;

    private JacksonTester<List<Portfolio>> jackPortfolioList;
    private JacksonTester<Portfolio> jackPortfolio;
    private JacksonTester<Profile> jackProfile;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void all() throws Exception {

        //GIVEN
        given(portfolioTweetMediator.getAllPortfolio())
                .willReturn(buildPortfolios());

        //WHEN
        MockHttpServletResponse response = mvc.perform(
                get("/api/portfolios")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //THEN
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jackPortfolioList.write(buildPortfolios()).getJson(), response.getContentAsString());
        verify(portfolioTweetMediator).getAllPortfolio();
    }

    @Test
    void one() throws Exception {

        Profile profile = buildProfile();

        //GIVEN
        given(portfolioTweetMediator.getProfile(any(Long.class)))
                .willReturn(profile);

        //WHEN
        MockHttpServletResponse response = mvc.perform(
                get(String.format("/api/portfolios/%d", profile.getPortfolio().getId()))
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //THEN
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(mapper.writeValueAsString(profile), response.getContentAsString());
        verify(portfolioTweetMediator).getProfile(any(Long.class));
    }

    @Test
    void partialUpdateName() throws Exception {

        Portfolio portfolio = TestBuilder.buildUpdateObj();
        String jsonPortfolio = mapper.writeValueAsString(portfolio);

        //GIVEN
        given(portfolioTweetMediator.updatePortfolio(any(Long.class), any(Portfolio.class)))
                .willReturn(portfolio);

        //WHEN
        MockHttpServletResponse response = mvc.perform(
                patch(String.format("/api/portfolios/%d", PORTAFOLIO.getId()))
                        .content(jsonPortfolio)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        //THEN
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonPortfolio, response.getContentAsString());

        verify(portfolioTweetMediator).updatePortfolio(any(Long.class), any(Portfolio.class));
    }
}