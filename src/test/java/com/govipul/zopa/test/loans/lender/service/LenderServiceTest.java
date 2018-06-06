package com.govipul.zopa.test.loans.lender.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.govipul.zopa.test.loans.lender.model.Lender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LenderServiceTest {

	private static final String PATH = "C:\\Personal\\Projects\\Zopa\\Market Data for Exercise.csv";

	@Autowired
	private LenderService lenderService;

	@Test
	public void testLenderServiceLoad() throws Exception {
		List<Lender> loadLender = lenderService.loadLender(PATH);
		assertNotNull(loadLender);
	}

}
