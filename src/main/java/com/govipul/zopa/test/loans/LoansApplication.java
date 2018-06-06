package com.govipul.zopa.test.loans;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.govipul.zopa.test.loans.exception.LoanException;
import com.govipul.zopa.test.loans.lender.model.Lender;
import com.govipul.zopa.test.loans.lender.service.LenderService;
import com.govipul.zopa.test.loans.quote.model.Quote;
import com.govipul.zopa.test.loans.quote.service.QuoteService;
import com.govipul.zopa.test.loans.validation.service.DataValidationService;

@SpringBootApplication
public class LoansApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoansApplication.class);
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private LenderService lenderService;
	@Autowired
	private DataValidationService dataValidationService;

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			final double amount = Double.parseDouble(args[1]);
			final String filePath = args[0];
			dataValidationService.validateInputData(filePath, amount);
			List<Lender> lendersData = lenderService.loadLender(filePath);
			dataValidationService.checkAvailiabilty(amount, lendersData);
			Quote quotes = quoteService.getQuotes(lendersData, amount);
			LOGGER.info("===============================================================");
			LOGGER.info(quotes.toString());
			LOGGER.info("===============================================================");
		} catch (LoanException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}