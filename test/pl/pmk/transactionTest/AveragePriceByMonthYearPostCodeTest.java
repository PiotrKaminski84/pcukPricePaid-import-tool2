package pl.pmk.transactionTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import pl.pmk.transaction.AveragePriceByMonthYearPostCode;

public class AveragePriceByMonthYearPostCodeTest {

	private static AveragePriceByMonthYearPostCode avgPrice; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		avgPrice = new AveragePriceByMonthYearPostCode();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void CreateOk() {
		
		assertNotNull(avgPrice);
	}
	
	@Test
	public void updateOkfromEmpty(){
		avgPrice.update(1, 100);
		assertEquals(100, avgPrice.getAveragePrice());
		assertEquals(1, avgPrice.getNoTransactions());
		avgPrice.update(1, 200);
		assertEquals(150, avgPrice.getAveragePrice());
		assertEquals(2, avgPrice.getNoTransactions());
	}

}
