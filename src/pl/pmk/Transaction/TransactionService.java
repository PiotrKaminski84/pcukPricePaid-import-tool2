package pl.pmk.transaction;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import pl.pmk.bussines.PostCodeService;

public interface TransactionService {

	public List<Transaction> readAllFromFile(String path);
	public void PrintList ();
	public List<Transaction> getListOfTransactions();
	public void setListOfTransactions(List<Transaction> listOfTransactions);
	public double calculateAveragePrice();
	public Map<String,Double> calculateAveragePriceByPostcode();
	public List<Transaction> getTransactions(Predicate<Transaction> p);
	public List<Transaction> getTransactionsForPostCode(String postcode);
	public Map<String,Double> getAveragePriceWithinRadiousByPostCode(String postcode, double radious);
	public double getAveragePriceWithinRadious(String postcode, double radious);	
	public PostCodeService getPostCodeService();
	public void setPostCodeService(PostCodeService postCodeService);
	public void emptyTransactionList();	
	public void uploadAvgByPcYearMonthToDb();
}
