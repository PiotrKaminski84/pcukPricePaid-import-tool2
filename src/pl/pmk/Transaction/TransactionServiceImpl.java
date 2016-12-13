package pl.pmk.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.pmk.bussines.PostCode;
import pl.pmk.bussines.PostCodeService;
import static pl.pmk.Transaction.TransactionPredicate.*;


public class TransactionServiceImpl implements TransactionService{

	private List<Transaction> listOfTransactions;
	private PostCodeService postCodeService;

	public PostCodeService getPostCodeService() {
		return postCodeService;
	}

	public void setPostCodeService(PostCodeService postCodeService) {
		this.postCodeService = postCodeService;
	}

	@Override
	public List<Transaction> readAllFromFile(String path) {
		if (listOfTransactions==null){
			listOfTransactions = new ArrayList<>();
		}
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			String buffer = br.readLine();
			Transaction transaction;
			while(buffer!=null){
				transaction = new Transaction(buffer);
				listOfTransactions.add(transaction);
				buffer=br.readLine();
			}
			br.close();
		}catch (IOException e){
			System.out.println("problem reading File");			
		}		
		return listOfTransactions;
	}

	public List<Transaction> getListOfTransactions() {
		return listOfTransactions;
	}
	public void setListOfTransactions(List<Transaction> listOfTransactions) {
		this.listOfTransactions = listOfTransactions;
	}

	@Override
	public void PrintList() {
		listOfTransactions.forEach(a->System.out.println(a));		
	}

	@Override
	public double calculateAveragePrice() {
		OptionalDouble avgPrice;
		avgPrice = listOfTransactions.stream().mapToDouble(t->t.getPricePaid()).average();
		if (avgPrice.isPresent())
			return avgPrice.getAsDouble();
		return 0;
	}

	@Override
	public Map<String, Double> calculateAveragePriceByPostcode() {
		Map<String, Double> mapByPostCode = listOfTransactions.stream().collect(Collectors.groupingBy(w->w.getPostcode(),Collectors.averagingLong(w->w.getPricePaid())));		
		return mapByPostCode;
	}

	@Override
	public List<Transaction> getTransactions(Predicate<Transaction> p) {
		return listOfTransactions.stream().filter(p).collect(Collectors.toList());
	}

	@Override
	public List<Transaction> getTransactionsForPostCode(String postcode) {		
		return filterTransactions(postCodeIs(postcode), listOfTransactions).collect(Collectors.toList());
	}

	@Override
	public Map<String, Double> getAveragePriceWithinRadiousByPostCode(String postcode, double radious) {
		List<String> postCodes = postCodeService.postCodesWithinRadious(postcode, radious);
		List<Transaction> transactions = new ArrayList<>();
		transactions = listOfTransactions.stream().filter(isOnPostCodeList(postCodes)).collect(Collectors.toList());
		return transactions.stream().collect(Collectors.groupingBy(t->t.getPostcode(),Collectors.averagingLong(t->t.getPricePaid())));		
	}

	@Override
	public double getAveragePriceWithinRadious(String postcode, double radious) {
		List<String> postCodes = postCodeService.postCodesWithinRadious(postcode, radious);
		List<Transaction> transactions = new ArrayList<>();
		transactions = listOfTransactions.stream().filter(isOnPostCodeList(postCodes)).collect(Collectors.toList());
		return transactions.stream().mapToInt(a->a.getPricePaid()).average().getAsDouble();		
	}

	@Override
	public Map<MonthYear, Double> getAveragePriceMonthly(Date startDate, Date endDate, String postcode, double radious) {
		//Date search not implemented
		List<String> postCodes = postCodeService.postCodesWithinRadious(postcode, radious);
		Stream<Transaction> transactions = filterTransactions(isOnPostCodeList(postCodes).and(dateAfter(startDate)).and(dateBefore(endDate)), listOfTransactions);		
		return transactions.collect(Collectors.groupingBy(t->extractMonthYear(t.getDateOfTransfer()), Collectors.averagingDouble(t->t.getPricePaid())));
	}

	
	private MonthYear extractMonthYear(Date date){
		Calendar cal =Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		return new MonthYear(month,year);
	}

	@Override
	public void emptyTransactionList() {
		listOfTransactions = null;		
	}

}

