package com.govipul.zopa.test.loans.quote.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import com.govipul.zopa.test.loans.lender.model.Lender;
import com.govipul.zopa.test.loans.quote.model.Quote;

@Service
public class QuoteService {

	private static final Integer TENURE_IN_MONTHS = 36;

	public Quote getQuotes(List<Lender> lendersData, final double requestedAmount) {
		double roi = getRateOfinterest(lendersData, requestedAmount);
		double monthlyRepayment = getMonthlyRepayment(roi, requestedAmount);

		Quote requestedQuotation = new Quote();
		requestedQuotation.setRequestedAmount(requestedAmount);
		requestedQuotation.setMonthlyPayment(monthlyRepayment);
		requestedQuotation.setTotalRepayment(monthlyRepayment * TENURE_IN_MONTHS);
		requestedQuotation.setRate(roi);

		return requestedQuotation;
	}

	/**
	 * EMI= [P x R x (1+R)^N]/[(1+R)^N-1]; P stands principal; ROI is the interest
	 * rate per month;N is the number of monthly (36)
	 * 
	 * @param roi
	 */
	protected double getMonthlyRepayment(double roi, final double principal) {
		BigDecimal monthlyInterest = (new BigDecimal(roi)).divide(new BigDecimal(12.0), 4, RoundingMode.HALF_UP);
		BigDecimal commonFactor = BigDecimal.ONE.add(monthlyInterest).pow(TENURE_IN_MONTHS);
		BigDecimal dividend = commonFactor.multiply(new BigDecimal(principal)).multiply(monthlyInterest);
		BigDecimal divisior = commonFactor.subtract(BigDecimal.ONE);
		return dividend.divide(divisior, MathContext.DECIMAL64).doubleValue();
	}

	protected double getRateOfinterest(final List<Lender> lendersData, final double requestedAmount) {
		double totalroi = 0;
		int index = 0;
		double remainingAmount = requestedAmount;
		while (remainingAmount > 0) {
			Lender lender = lendersData.get(index++);
			remainingAmount -= lender.getAvailableAmount();
			totalroi += lender.getRate();
		}
		return Precision.round((totalroi / index), 2);
	}

}
