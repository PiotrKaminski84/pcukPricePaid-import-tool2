package pl.pmk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.OptionalDouble;

import pl.pmk.Transaction.Transaction;

public class ReaderApp {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Transaction> tranList;
		String path = "C:\\Users\\elpmk\\Desktop\\data\\LondonList.txt";
		ObjectInputStream fr = new ObjectInputStream(new FileInputStream(new File(path)));
		System.out.println(System.currentTimeMillis());
		tranList = (List<Transaction>) fr.readObject();
		System.out.println(System.currentTimeMillis());
		OptionalDouble sumofPrice;		
		System.out.println(System.currentTimeMillis());
		for (int i=1995; i<2016;i++){
			int year =i;
			//tranList.stream().forEach(a->System.out.println(a.getDateOfTransfer().getYear()));
			sumofPrice =  tranList.stream().filter(p-> p.getDateOfTransfer().getYear()+1900==year).mapToDouble(a-> a.getPricePaid()).average();
			System.out.println(" avg price in year" + year +" was : " + sumofPrice);
			System.out.println(System.currentTimeMillis());
		}
	}

}
