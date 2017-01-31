package pl.pmk.access;

import java.sql.SQLException;
import java.util.List;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import pl.pmk.transaction.AvgNoTrans;

public class AvgNoTransDaoImpl implements AvgNoTransDao {
	
	private DbConnector db;
	private Connection conn;
	private Statement stmt;
	
	

	public AvgNoTransDaoImpl() {
		super();
		db = new DbConnectorImpl();
		conn = db.getConnection();
		try {
			stmt = (Statement) conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Couldnt create statement");
			e.printStackTrace();
		}
	}

	@Override
	public AvgNoTrans getRecord(int year, int month, String postCode) {
		
		
			return new AvgNoTrans(120);
					
	}

	@Override
	public void addRecord(int year, int month, String postCode, int noOfTransactions, double avgPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRecord(int year, int month, String postCode, int noOfTransactions, double avgPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean recordExist(int year, int month, String postCode, int noOfTransactions, double avgPrice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AvgNoTransDao> getReordsForPostCode(String postCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AvgNoTransDao> getReordsForPostCodeYear(String postCode, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AvgNoTransDao> getReordsForPostCodeYearMont(String postCode, int year, int month) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	@Override
	public void uploadAvgByPcYearMonthToDb(Map<PcMonthYear, AvgNoTrans> pcMonthYearAverageData) {
		//
		ConnectDb();
		try {
			statement = connection.createStatement();
			for (Map.Entry<PcMonthYear, AvgNoTrans> a : pcMonthYearAverageData.entrySet()){
				String pc = a.getKey().getPostCode();
				int year = a.getKey().getYear();
				int month = a.getKey().getMonth();
				double avgPrice = a.getValue().getAveragePrice();
				int noOfTransactions = a.getValue().getNumberOfTransactions();
				String query = "select * from avg_price where post_code = '" + pc + "' and year = "+ year + " and month = "+ month;
				statement.executeQuery(query);
				if (statement.getResultSet().next()){
					System.out.println("entry exists");					
				}else {
					query = "insert into avg_price (post_code,year,month,avg_price,no_of_transactions) values"
							+ " ('"+pc+"',"+year+","+month+","+avgPrice+","+noOfTransactions+")";					
					statement.execute(query);
					//connection.commit();
				}
			}

		} catch (SQLException e) {
			System.out.println("Couldn't create statement !");
			e.printStackTrace();
		}
		Discconect();
		
	}*/
}
