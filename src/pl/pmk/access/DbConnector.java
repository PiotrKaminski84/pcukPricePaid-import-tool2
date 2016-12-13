package pl.pmk.access;

import java.io.File;
import java.util.Map;

import pl.pmk.bussines.PostCode;
import pl.pmk.bussines.PropertyDetails;
import pl.pmk.bussines.SaleTransaction;

public interface DbConnector {
	
	
	public void insertSaleTransaction(SaleTransaction trans, PropertyDetails propertyDetails);
	public void creatTablesForTransaction();
	public void startBatchQuerry();
	public void addRecordToBatchStatment(SaleTransaction saleTranasaction, PropertyDetails propertyDetails);
	public void endBatchQuerry();
	

}
