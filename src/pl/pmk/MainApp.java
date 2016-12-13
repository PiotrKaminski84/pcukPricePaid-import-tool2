package pl.pmk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import pl.pmk.Transaction.MonthYear;
import pl.pmk.Transaction.TransactionService;
import pl.pmk.Transaction.TransactionServiceImpl;
import pl.pmk.bussines.PostCodeService;
import pl.pmk.bussines.PostCodeServiceImpl;

public class MainApp {


	public static void main(String[] args) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss");
		System.out.println(sdf.format(new Date(0)));
		System.out.println("Starting -reading psotcodes" + sdf.format(new Date(System.currentTimeMillis())));

		PostCodeService postCodeService = new PostCodeServiceImpl();
		String postCodePath = "C:\\Users\\elpmk\\Desktop\\data\\ukpostcodes\\ukpostcodes.csv";		
		postCodeService.loadPostCodesFromFile(postCodePath);
		//postCodeService.postCodesWithinRadious("LE11 5EX", 0.1).forEach(a->System.out.println(a));

		TransactionService transactionService = new TransactionServiceImpl();
		transactionService.setPostCodeService(postCodeService);
		String path = "C:\\Users\\elpmk\\Desktop\\data\\pp";	
		String savePath = "C:\\Users\\elpmk\\Desktop\\data\\generated\\";
		File folder = new File(path);		
		Arrays.asList(folder.listFiles()).forEach(f->{
			transactionService.emptyTransactionList();
			System.out.println(f.getAbsolutePath());
			transactionService.readAllFromFile(f.getAbsolutePath());
			System.out.println(f.getPath() + " read " + sdf.format(new Date(System.currentTimeMillis())));
			Map<MonthYear, Double> avgByPbDate = transactionService.getAveragePriceMonthly(new Date(0), new Date(System.currentTimeMillis()), "LE11 5EX", 2);
			System.out.println("data processed " + sdf.format(new Date(System.currentTimeMillis())));
			File saveFile = new File(savePath+f.getName());			
			try {
				saveFile.createNewFile();
				PrintWriter pw = new PrintWriter(saveFile);
				for(Entry<MonthYear, Double> a : avgByPbDate.entrySet()){
					pw.println(a.getKey() + " " + a.getValue());
				}
				pw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("saved as txt " + sdf.format(new Date(System.currentTimeMillis())));

		});
		//System.out.println(transactionService.getAveragePriceWithinRadious("LE11 5EX", 2));

		//transactionService.PrintList();
		//System.out.println(transactionService.calculateAveragePrice());

		//Map<String,Double> avgPriceByPostCode = transactionService.calculateAveragePriceByPostcode();		
		//avgPriceByPostCode.forEach((k,v)->System.out.println(k + " £" + v));		

		//transactionService.getTransactions(p->p.getPostcode().equals("LE11.*")).forEach(a->System.out.println(a.getStreet() + " " + a.getPricePaid()));
	}
}
