package pl.pmk.service;

import pl.pmk.bussines.PropertyDetails;
import pl.pmk.bussines.SaleTransaction;

public interface SaleRecordService {
	
	public PropertyDetails extractPropertyDetails(String str);
	public SaleTransaction extractSaleTransaction(String str);

}
