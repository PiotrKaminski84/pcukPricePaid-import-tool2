package pl.pmk.access;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class DbConnectorTest {

	private DbConnector db;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		db = new DbConnectorImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void connectionTest() throws SQLException {		
		assertNotNull(db.getConnection().createStatement());		
	}
	
	@Test(expected=MySQLNonTransientConnectionException.class)
	public void disconnectTest() throws SQLException{
		db.Disconnect();
		db.getConnection().createStatement();
	}

}
