package com.govipul.zopa.test.loans.quote.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Quote {
	private double requestedAmount;
	private double rate;
	private double monthlyPayment;
	private double totalRepayment;

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate * 100;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public double getTotalRepayment() {
		return totalRepayment;
	}

	public void setTotalRepayment(double totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Requested Amount:£%.1f", this.getRequestedAmount())).append("\n")
				.append(String.format("Rate:%.1f%%", this.getRate())).append("\n")
				.append(String.format("Monthly repayment:£%.2f", this.getMonthlyPayment())).append("\n")
				.append(String.format("Total repayment:£%.2f", this.getTotalRepayment()));
		return builder.toString();

	}
}
