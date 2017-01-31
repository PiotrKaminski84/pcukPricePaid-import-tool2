package pl.pmk.access;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.pmk.transaction.AveragePriceByMonthYearPostCode;

public class AveragePriceByMYPDaoTest {

	public static AveragePriceByMYPDao dao; 
	
	@Before
	public void setUp() throws Exception {
		dao = new AveragePriceByMYPDaoImpl();
		dao.updateRecord("test", 1, 1, 1, 100000);
		dao.updateRecord("test", 1, 3, 1, 100000);
		dao.updateRecord("test", 1, 2, 1, 120000);
	}

	@After
	public void tearDown(){
		DbConnector db = new DbConnectorImpl();
		try {
			db.getConnection().createStatement().execute("delete from pcdb.avg_price where post_code='test'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void retrivePostCodeDataTest() {
		List<AveragePriceByMonthYearPostCode> resultList = dao.getRecords("test");
		assertEquals(3,	 resultList.size());
		assertEquals("test", resultList.get(0).getPostCode());		
	}
	
	
	@Test
	public void retirvePostCodeYearMonthDataTest(){
		AveragePriceByMonthYearPostCode result = dao.getRecord("test", 1, 2);
		assertEquals(120000, result.getAveragePrice(), 0.0);		
		
	}
	
	@Test
	public void updateTest(){
		dao.updateRecord("test", 1, 1, 1, 100000);
		assertEquals(dao.getRecord("test", 1, 1).getAveragePrice(), 100000, 0.01);
	}

}
