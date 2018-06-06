package com.govipul.zopa.test.loans.validation.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.govipul.zopa.test.loans.exception.LoanException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DataValidationServiceTest {

	@Autowired
	private DataValidationService validationService;

	private static final String PATH = "C:\\Personal\\Projects\\Zopa\\Market Data for Exercise.csv";

	@Test
	public void testValidateInputData_success() throws LoanException {
		validationService.validateInputData(PATH, 12000);
	}

	@Test(expected = LoanException.class)
	public void testValidateInputData_null_filepath() throws LoanException {
		validationService.validateInputData(null, 12000);
	}

	@Ignore
	@Test(expected = NumberFormatException.class)
	public void testValidateInputData_invalidNumber() throws LoanException {
		// validationService.validateInputData(PATH, "RRGGGIII");
	}

	@Test(expected = LoanException.class)
	public void testValidateInputData_number_out_of_range() throws LoanException {
		validationService.validateInputData(PATH, 18000);
	}

	@Test
	public void testIsMultiple_sucess() {
		boolean isMultiple = validationService.isMultiple(12000, 100);
		assertTrue(isMultiple);
	}

	@Test
	public void testIsMultiple_not_a_multiple() {
		boolean isMultiple = validationService.isMultiple(1569, 100);
		assertFalse(isMultiple);
	}

}
