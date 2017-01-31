package pl.pmk.access;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DbConnectorImpl implements DbConnector {

	
	/*
	 * db.driver = com.mysql.jdbc.Driver
db.url = jdbc:mysql://localhost:3306/pcdb
db.username = java
db.password = javahaslo

	 */
	
	private static String userName = "java";
	private static String password = "javahaslo";
	private static String url = "jdbc:mysql://localhost:3306/pcdb";
	public Connection connection;
	public Statement statement;
	
	public DbConnectorImpl(){
		ConnectDb();		
	}
	
	
	public void ConnectDb(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = (Connection) DriverManager.getConnection(url, userName, password);
			
			System.out.println("DB connected");
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
	
	@Override
	public void Disconnect(){
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("couldnt close connection");
			e.printStackTrace();
		}
	}
	
	

	@Override
	public com.mysql.jdbc.Connection getConnection() {		
		return connection;
	}





	
	
}
