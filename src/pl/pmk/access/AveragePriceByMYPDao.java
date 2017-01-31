package pl.pmk.access;

import java.util.List;

import pl.pmk.transaction.AveragePriceByMonthYearPostCode;

public interface AveragePriceByMYPDao {
	
	public List<AveragePriceByMonthYearPostCode> getRecords(String postCode);
	public List<AveragePriceByMonthYearPostCode> getRecords(String postCode, int year);
	public AveragePriceByMonthYearPostCode getRecord(String postCode, int year, int month);
	public void updateRecord(String postCode, int year, int month, int noTransactions, double avgPrice);

}
