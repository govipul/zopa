package com.govipul.zopa.test.loans.quote.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.govipul.zopa.test.loans.exception.LoanException;
import com.govipul.zopa.test.loans.lender.model.Lender;
import com.govipul.zopa.test.loans.lender.service.LenderService;
import com.govipul.zopa.test.loans.quote.model.Quote;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuoteServiceTest {

	private static final String PATH = "C:\\Personal\\Projects\\Zopa\\Market Data for Exercise.csv";

	@Autowired
	private QuoteService quoteService;
	@Autowired
	private LenderService lenderService;

	private List<Lender> loadLender = null;

	private final double amount = 2300d;

	@Before
	public void init() throws LoanException {
		loadLender = lenderService.loadLender(PATH);
	}

	@Test
	public void testGetQuotes() {
		Quote quotes = quoteService.getQuotes(loadLender, amount);
		assertNotNull(quotes);
	}

	@Test
	public void testCalculateEMI() {
		double monthlyRepayment = quoteService.getMonthlyRepayment(0.07, amount);
		assertTrue(monthlyRepayment > 0);
	}

	@Test
	public void testGetRateOfinterest() {
		double rateOfinterest = quoteService.getRateOfinterest(loadLender, amount);
		assertEquals(0.07, rateOfinterest, 0);
	}

}
