package pl.pmk.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.pmk.transaction.AveragePriceByMonthYearPostCode;

public class AveragePriceByMYPDaoImpl implements AveragePriceByMYPDao {


	private DbConnector db;

	public AveragePriceByMYPDaoImpl() {
		super();
		db = new DbConnectorImpl();
	}

	@Override
	public List<AveragePriceByMonthYearPostCode> getRecords(String postCode) {
		String query = "Select * from avg_price where post_code = '" + postCode + "'";
		List<AveragePriceByMonthYearPostCode> resultList = new ArrayList<>();
		try {
			Statement  stmt = db.getConnection().createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				resultList.add(extractFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB connection problem...");			
		}
		return resultList;
	}

	@Override
	public List<AveragePriceByMonthYearPostCode> getRecords(String postCode, int year) {
		String query = "Select * from avg_price where year = "+ year + " post_code = '" + postCode + "'";
		List<AveragePriceByMonthYearPostCode> resultList = new ArrayList<>();
		try {
			Statement  stmt = db.getConnection().createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next()){
				resultList.add(extractFromResultSet(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB connection problem...");			
		}
		return resultList;
	}

	@Override
	public AveragePriceByMonthYearPostCode getRecord(String postCode, int year, int month) {
		String query = "Select * from pcdb.avg_price where year = " + year + " and month = " + month + " and post_code = '" + postCode + "'";
		List<AveragePriceByMonthYearPostCode> resultList = new ArrayList<>();
		try {
			Statement  stmt = db.getConnection().createStatement();
			stmt.executeQuery(query);

			ResultSet rs = stmt.getResultSet();
			if (rs.next()){				
				resultList.add(extractFromResultSet(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB connection problem...");			
		}
		if (resultList.size()==0){
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public void updateRecord(String postCode, int year, int month, int noTransactions, double avgPrice) {
		AveragePriceByMonthYearPostCode actual = getRecord(postCode, year, month);
		String query;
		if (actual==null){
			actual = new AveragePriceByMonthYearPostCode(postCode,year,month,noTransactions,avgPrice);
			query="Insert into pcdb.avg_price (post_code, year, month, no_of_transactions, avg_price) values ('"+ actual.getPostCode()+"'"
				+ ", "+ actual.getYear() + ", "+ actual.getMonth() + ", "+ actual.getNoTransactions()+ ", "+ actual.getAveragePrice()+ ")";
		}else{
			actual.update(noTransactions, avgPrice);
			query="Update pcdb.avg_price set no_of_transactions = "+ actual.getNoTransactions()+ ", avg_price="+ actual.getAveragePrice() 
					+ " where post_code = '"+ actual.getPostCode()+"' and year ="+ actual.getYear() + " and month = "+ actual.getMonth() + ";";
		}
		
		try {
			Statement stmt = db.getConnection().createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	private AveragePriceByMonthYearPostCode extractFromResultSet(ResultSet rs) throws SQLException{
		String postCode = rs.getString(1);
		int year = rs.getInt(2);
		int month = rs.getInt(3);
		double avgPrice = rs.getDouble(4);
		int noOfTransactinons = rs.getInt(5);

		return new AveragePriceByMonthYearPostCode(postCode, year,month,noOfTransactinons, avgPrice);		
	}


}
