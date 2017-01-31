package pl.pmk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.pmk.bussines.PropertyDetails;
import pl.pmk.transaction.Transaction;

public class Application {
	

	private static String path;
	private static String userName = "java";
	private static String password = "javahaslo";
	private static int batchCounter;
	private static int batchSize=1000000;
	private static String url ="jdbc:mysql://localhost:3306/pcdb"; //"jdbc:mysql://192.168.1.8:3306/pcdb";//
	private static BufferedReader reader;
	public static final int DETACHED = 1;
	public static final int SEMI_DETACHED = 2;
	public static final int TERRACED = 3;
	public static final int FLAT = 4;
	public static final int OTHER = 5;
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
		//sql
		//Connection connection;
		//Statement statement;
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		//connection = (Connection) DriverManager.getConnection(url, userName, password);
		//statement  =  connection.createStatement();
		//statement.executeUpdate("create table if not exists `transaction` (	`id` int(14) not null Auto_increment,  `tranid` varchar(36), `pricepaid` varchar(10), `transactiondate` date,`postcode` varchar(8), `oldNew` varchar(1), `tennure` varchar(1),`propertytype` int(1),    `paon` varchar(200),    `saon` varchar(200),    `street` varchar(50),    `locality` varchar(100),    `town` varchar(100), `district` varchar(100),   `county` varchar(100),    primary key (`id`))");		
		//statement.close();
		//file
		System.out.println(System.currentTimeMillis());
		String buffer;
		List<Transaction> tranList = new ArrayList<>();
		Transaction testTransaction;
		path = "C:\\Users\\elpmk\\Desktop\\data\\pp-complete.txt";
		//path = "g:\\data\\ukpostcodesmysql\\ukpostcodesmysql.sql";
		try {
			reader = new BufferedReader(new FileReader(new File(path)));
			buffer = reader.readLine();	
			//statement = connection.createStatement();
			int counter =0;
			while (buffer!=null){				
				testTransaction = transactionFromString(buffer);
				if (testTransaction.getTown().equalsIgnoreCase("London"))
					tranList.add(testTransaction);
			//statement.addBatch(generateInsert(buffer));
				//statement.addBatch(buffer);
				counter++;
				buffer = reader.readLine();
				if (counter%batchSize==0){
					System.out.println(counter +" "+ System.currentTimeMillis());
					//counter=0;
					//System.out.println(generateInsert(buffer));
					//statement.executeBatch();
					//statement = connection.createStatement();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		//statement.executeBatch();
		//connection.close();		
		
		System.out.println(System.currentTimeMillis());
		System.out.println(tranList.size());
		path = "C:\\Users\\elpmk\\Desktop\\data\\LondonList.txt";
		reader.close();
		//PrintWriter fr = new PrintWriter(new FileWriter(new File(path)));
		ObjectOutputStream fr =new ObjectOutputStream( new FileOutputStream(new File(path)));
		//tranList.forEach(a-> fr.println(a));
		fr.writeObject(tranList);
		fr.close();
		System.out.println(System.currentTimeMillis());
		
	}


	private static String generateInsert(String str) {
		str=str.replaceAll("'", "");
		String[] split =str.split(",");
		int propertyType;
		PropertyDetails propertyDetails;
		switch (split[4].substring(1,1)){
		case "D" :
			propertyType = DETACHED;
			break;
		case "S" :
			propertyType = SEMI_DETACHED;
			break;
		case "T" :
			propertyType = TERRACED;
			break;
		case "F" :
			propertyType = FLAT;
			break;
		default:
			propertyType = OTHER;
		}
		String postcode = split[3];
		postcode = postcode.replaceAll("\"","");
		String pAON = split[7];
		pAON=pAON.replaceAll("\"","");
		String sAON = split[8];
		sAON=sAON.replaceAll("\"","");
		String street = split[9];
		street=street.replaceAll("\"","");
		String locality = split[10];
		locality=locality.replaceAll("\"","");
		String town = split[11];
		town=town.replaceAll("\"","");
		String district = split[12];
		district=district.replaceAll("\"","");
		String county = split[13];
		county=county.replaceAll("\"","");
		String id;		
		Date dateOfTransfer;
		char tennure;
		char oldNew;
		int pricePaid;
				
		id = split[0].replace("{", "");
		id=id.replace("}", "");
		String date = split[2].substring(0, split[2].indexOf(' '));
		dateOfTransfer = Date.valueOf(date);
		tennure = split[6].charAt(1);
		oldNew = split[5].charAt(1);
		pricePaid = Integer.parseInt(split[1]);
		String returnQ = "insert into transaction (	tranid, pricepaid, transactiondate,postcode, oldNew, tennure,propertytype,    paon,    saon,    street,    locality,    town, district,   county )values  ( '"+id+"',"+ pricePaid+",'"+dateOfTransfer+"','"+postcode+"','"+oldNew+"','"+tennure+"','"+propertyType+"','"+pAON+"','"+sAON+"','"+street+"','"+locality+"','"+town+"','"+district+"','"+county+"');";								
		
		return returnQ;
	}
	private static Transaction transactionFromString(String str) {
		str=str.replaceAll("'", "");
		String[] split =str.split(",");
		int propertyType;
		PropertyDetails propertyDetails;
		switch (split[4].substring(1,1)){
		case "D" :
			propertyType = DETACHED;
			break;
		case "S" :
			propertyType = SEMI_DETACHED;
			break;
		case "T" :
			propertyType = TERRACED;
			break;
		case "F" :
			propertyType = FLAT;
			break;
		default:
			propertyType = OTHER;
		}
		String postcode = split[3];
		postcode = postcode.replaceAll("\"","");
		String pAON = split[7];
		pAON=pAON.replaceAll("\"","");
		String sAON = split[8];
		sAON=sAON.replaceAll("\"","");
		String street = split[9];
		street=street.replaceAll("\"","");
		String locality = split[10];
		locality=locality.replaceAll("\"","");
		String town = split[11];
		town=town.replaceAll("\"","");
		String district = split[12];
		district=district.replaceAll("\"","");
		String county = split[13];
		county=county.replaceAll("\"","");
		String id;		
		Date dateOfTransfer;
		char tennure;
		char oldNew;
		int pricePaid;
				
		id = split[0].replace("{", "");
		id=id.replace("}", "");
		String date = split[2].substring(0, split[2].indexOf(' '));
		dateOfTransfer = Date.valueOf(date);
		tennure = split[6].charAt(1);
		oldNew = split[5].charAt(1);
		pricePaid = Integer.parseInt(split[1]);							
		Transaction retTransaction =new Transaction(postcode, pAON, sAON, street, locality, town, district, county, id, dateOfTransfer, tennure, oldNew, pricePaid);
		return retTransaction;
	}
	

}



/*package pl.pmk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.pmk.access.DbConnector;
import pl.pmk.access.DbConnectorImpl;
import pl.pmk.service.FileService;
import pl.pmk.service.FileServiceImpl;

public class Application {
	
	private static String userName = "java";
	private static String password = "javahaslo";
	private static int batchCounter;
	private static int batchSize=100000;
	private static String url = "jdbc:mysql://localhost:3306/pcdb";
	static Connection connection;
	static Statement statement;
	
	public static void main(String[] args) throws IOException, SQLException {
		
		
		String path = "H:\\data\\ukpostcodesmysql\\ukpostcodesmysql.sql";
		
		
		path = "H:\\data\\pp-complete.txt";
		String line;
		List<String> transactionList = new ArrayList<>();
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		line = reader.readLine();
		while (line!=null){
			transactionList.add(line);
			line = reader.readLine();
		}
		//Reading all transaction data into a labdat
		long t=System.currentTimeMillis();
		System.out.println(transactionList.size());		
		
		System.out.println(System.currentTimeMillis()-t);
		
		//sql - batch processing
		/*
		 * long t=System.currentTimeMillis();
		
		long t1=t;
		long diff;
		System.out.println(t);
		String command;
		connection = DriverManager.getConnection(url, userName, password);
		statement = connection.createStatement();
		command=reader.readLine();
		int count=0;
		while ((command!=null)){
			count++;
			if (count%batchSize==0){
				diff=System.currentTimeMillis()-t1;
				System.out.println(count+ " " + diff);
				statement.executeBatch();
				statement = connection.createStatement();
				t1=System.currentTimeMillis();				
			}
			statement.addBatch(command);
			command=reader.readLine();
			
		}
		System.out.println(System.currentTimeMillis()-t);
		statement.executeBatch();
		connection.close();
	
		 
	}

}
*/