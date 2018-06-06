package com.govipul.zopa.test.loans.validation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.govipul.zopa.test.loans.exception.LoanException;
import com.govipul.zopa.test.loans.lender.model.Lender;

@Service
public class DataValidationService {

	public void validateInputData(String filePath, double requestedAmount) throws LoanException {

		if (StringUtils.isEmpty(filePath) || !filePath.toLowerCase().endsWith(".csv")) {
			throw new LoanException("Please provide the valid CSV input file");
		}
		if (requestedAmount < 1000 || requestedAmount > 15000 || !isMultiple(requestedAmount, 100)) {
			throw new LoanException(
					"The loan amount requested should between £1000 and £15000 and should be multiple of £100");
		}
	}

	public void checkAvailiabilty(double requestedAmount, List<Lender> lendersData) throws LoanException {
		long totalAvailableSum = lendersData.stream().map(Lender::getAvailableAmount).mapToLong(Long::longValue).sum();
		if (totalAvailableSum < requestedAmount) {
			throw new LoanException(
					"The requested loan amount is not available with the system for now, maximum loan bank can provide is:£"
							+ totalAvailableSum);
		}
	}

	public boolean isMultiple(double amount, int factor) {
		return (amount % factor == 0);
	}
}
