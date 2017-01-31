package pl.pmk.access;


import com.mysql.jdbc.Connection;

public interface DbConnector {
	
	public void Disconnect();
	public Connection getConnection();	

}
