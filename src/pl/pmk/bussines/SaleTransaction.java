package pl.pmk.bussines;

import java.sql.Date;

public class SaleTransaction {
	
	private Date dateOfTransfer;
	private char Tennure;
	private char oldNew;
	private int pricePaid;
	
	public final char OLD = 'o';
	public final char NEW = 'n';
		
	public int getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(int pricePaid) {
		this.pricePaid = pricePaid;
	}
	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}
	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}
	public char getTennure() {
		return Tennure;
	}
	public void setTennure(char tennure) {
		Tennure = tennure;
	}
	public char getOldNew() {
		return oldNew;
	}
	public void setOldNew(char oldNew) {
		this.oldNew = oldNew;
	}
	public char getOLD() {
		return OLD;
	}
	public char getNEW() {
		return NEW;
	}

	
	

}
