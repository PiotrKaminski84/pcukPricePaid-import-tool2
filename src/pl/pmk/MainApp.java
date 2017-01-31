package pl.pmk;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import pl.pmk.bussines.PostCodeServiceImpl;
import pl.pmk.transaction.TransactionService;
import pl.pmk.transaction.TransactionServiceImpl;

public class MainApp {


	public static void main(String[] args) throws IOException {
		System.out.println(java.lang.Runtime.getRuntime().maxMemory());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss");
		System.out.println(sdf.format(new Date(0)));
		System.out.println("Starting -reading psotcodes" + sdf.format(new Date(System.currentTimeMillis())));
/*
	
		PostCodeService postCodeService = new PostCodeServiceImpl();
		String postCodePath = "C:\\Users\\elpmk\\Desktop\\data\\ukpostcodes\\ukpostcodes.csv";		
		postCodeService.loadPostCodesFromFile(postCodePath);
		System.out.println("End -reading psotcodes" + sdf.format(new Date(System.currentTimeMillis())));
		postCodeService.postCodesWithinRadious("LE11 5EX", 300).forEach(a->System.out.println(a));
		System.out.println("Cacl time" + sdf.format(new Date(System.currentTimeMillis())));
	*/
		TransactionService transactionService = new TransactionServiceImpl();
		transactionService.setPostCodeService(new PostCodeServiceImpl());
		String path = "C:\\Users\\elpmk\\Desktop\\data\\pp\\pp-2016.txt";	
		//String savePath = "C:\\Users\\elpmk\\Desktop\\data\\generated\\";
		//File folder = new File(path);		
		transactionService.readAllFromFile(path);
		System.out.println("Data read");
		transactionService.uploadAvgByPcYearMonthToDb();
		System.out.println("avg calculated and uploaded");
		
	/*	Arrays.asList(folder.listFiles()).forEach(f->{
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
		*/
		//System.out.println(transactionService.getAveragePriceWithinRadious("LE11 5EX", 2));

		//transactionService.PrintList();
		//System.out.println(transactionService.calculateAveragePrice());

		//Map<String,Double> avgPriceByPostCode = transactionService.calculateAveragePriceByPostcode();		
		//avgPriceByPostCode.forEach((k,v)->System.out.println(k + " £" + v));		

		//transactionService.getTransactions(p->p.getPostcode().equals("LE11.*")).forEach(a->System.out.println(a.getStreet() + " " + a.getPricePaid()));
	}
}
