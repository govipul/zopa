package com.govipul.zopa.test.loans.lender.model;

import org.springframework.context.annotation.Configuration;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

@Configuration
public class Lender implements Comparable<Lender> {

	@CsvBindByName(column = "Lender", required = true)
	@CsvBindByPosition(position = 0)
	private String name;

	@CsvBindByName(column = "Rate", required = true)
	@CsvBindByPosition(position = 1)
	private double rate;

	@CsvBindByName(column = "Available", required = true)
	@CsvBindByPosition(position = 2)
	private long availableAmount;

	public String getName() {
		return name;
	}

	public double getRate() {
		return rate;
	}

	public long getAvailableAmount() {
		return availableAmount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(String rate) {
		this.rate = Double.parseDouble(rate);
	}

	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = Long.parseLong(availableAmount);
	}

	@Override
	public int compareTo(Lender o) {
		Double thisRate = this.getRate();
		Double thatRate = o.getRate();
		return thisRate.compareTo(thatRate);
	}

	@Override
	public String toString() {
		return String.format("[Name: %s, Amount: %d%n, Interest Rate: %.3f] \n", name, availableAmount, rate);
	}
}
