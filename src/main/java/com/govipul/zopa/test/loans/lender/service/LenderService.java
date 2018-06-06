package com.govipul.zopa.test.loans.lender.service;

import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.govipul.zopa.test.loans.exception.LoanException;
import com.govipul.zopa.test.loans.lender.model.Lender;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class LenderService {

	public LenderService() {
		// Do nothing
	}

	public List<Lender> loadLender(String path) throws LoanException {
		List<Lender> csvToBeanBuilder;
		try {
			if (path == null || path.length() == 0) {
				throw new LoanException("The provided path is empty or ull, please provide the correct file path.");
			}
			csvToBeanBuilder = new CsvToBeanBuilder<Lender>(new FileReader(path)).withSkipLines(1)
					.withType(Lender.class).build().parse();
			Collections.sort(csvToBeanBuilder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoanException(e.getMessage(), e);
		}
		return csvToBeanBuilder;
	}
}
