package pl.pmk.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.pmk.bussines.PostCode;

public class TransactionPredicate {
	
	public static Predicate<Transaction> postCodeIs(String postcode){
		return p->p.getPostcode().equals(postcode);
	}
	
	public static Predicate<Transaction> isOnPostCodeList(List<String> list){		
		return p->list.contains(p.getPostcode());
	}
	
	public static Predicate<Transaction> dateBefore(Date date){
		return p-> p.getDateOfTransfer().compareTo(date)<=0?true:false; 
	}
	
	public static Predicate<Transaction> dateAfter(Date date){
		return p-> p.getDateOfTransfer().compareTo(date)>=0?true:false;
	}
	
	
	public static Stream<Transaction> filterTransactions(Predicate<Transaction> p, List<Transaction> list){
		return list.stream().filter(p);
	}

}
