package pl.pmk.access;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import java.sql.Connection;
import java.sql.Statement;

import pl.pmk.bussines.PostCode;
import pl.pmk.bussines.PropertyDetails;
import pl.pmk.bussines.SaleTransaction;

public class DbConnectorImpl implements DbConnector {

	
	/*
	 * db.driver = com.mysql.jdbc.Driver
db.url = jdbc:mysql://localhost:3306/pcdb
db.username = java
db.password = javahaslo

	 */
	
	private static String userName = "java";
	private static String password = "javahaslo";
	private static int batchCounter;
	private static int batchSize=1000;
	private static String url = "jdbc:mysql://localhost:3306/pcdb";
	Connection connection;
	Statement statement;
	
	
	private void ConnectDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void Discconect(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("couldnt close connection");
			e.printStackTrace();
		}
	}
	
	@Override
	public void creatTablesForTransaction() {
	
		ConnectDb();
		try {
			statement  =  connection.createStatement();
				statement.executeUpdate("create table if not exists `properetydetails` (	`id` int(14) not null Auto_increment,    `postcode` varchar(8),    `propertytype` int(1),    `paon` varchar(200),    `saon` varchar(200),    `street` varchar(50),    `locality` varchar(100),    `town` varchar(100),    `county` varchar(100),    primary key (`id`))");
				statement.executeUpdate("create table if not exists `transactiondetails` ( 	`id` varchar(36) not null,     `pricepaid` varchar(10),     `transactiondate` date,     `new` varchar(1),     `tennure` varchar(1),         primary key (`id`))");
				statement.close();
			Discconect();
		} catch (SQLException e) {
			System.out.println("couldnt get connection");
			e.printStackTrace();
		}
		
	}
	@Override
	public void startBatchQuerry() {
		ConnectDb();
		batchCounter=0;
	}
	@Override
	public void addRecordToBatchStatment(SaleTransaction saleTranasaction, PropertyDetails propertyDetails) {
		
		
		if (batchCounter==1000)
			try {
				statement.executeBatch();
			} catch (SQLException e) {
				System.out.println("problem executing batch statment");
				e.printStackTrace();
			}
		try {
			statement.addBatch(generateInsertQuery(propertyDetails));
			statement.addBatch(generateInsertQuery(saleTranasaction));
		} catch (SQLException e) {
			System.out.println("failed to addStatement");
			e.printStackTrace();
		}
		
		
	}
	
	private String generateInsertQuery(SaleTransaction st){
		
		return null;
		
	}
	
	private String generateInsertQuery(PropertyDetails pd){
		
		return null;
	}
	
	@Override
	public void endBatchQuerry() {
		
		Discconect();
		
	}

	@Override
	public void insertSaleTransaction(SaleTransaction trans, PropertyDetails propertyDetails) {
		// TODO Auto-generated method stub
		
	}

	public static int getBatchSize() {
		return batchSize;
	}

	public static void setBatchSize(int batchSize) {
		DbConnectorImpl.batchSize = batchSize;
	}
	
	
}
