package pl.pmk.access;

import java.util.List;

import pl.pmk.transaction.AvgNoTrans;

public interface AvgNoTransDao {
	
	public AvgNoTrans getRecord(int year, int month, String postCode);
	public void addRecord(int year, int month, String postCode, int noOfTransactions, double avgPrice);
	public void updateRecord(int year, int month, String postCode, int noOfTransactions, double avgPrice);
	public boolean recordExist(int year, int month, String postCode, int noOfTransactions, double avgPrice);
	public List<AvgNoTransDao> getReordsForPostCode(String postCode);
	public List<AvgNoTransDao> getReordsForPostCodeYear(String postCode, int year);
	public List<AvgNoTransDao> getReordsForPostCodeYearMont(String postCode, int year, int month);

}
