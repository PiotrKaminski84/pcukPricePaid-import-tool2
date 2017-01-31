package pl.pmk.transaction;

public class AvgNoTrans {
	
	private int numberOfTransactions;
	private double averagePrice;
	
	public AvgNoTrans(double pricePaid) {
		super();
		this.numberOfTransactions = 1;
		this.averagePrice = pricePaid;
	}
	
	public void addTransaction(double pricePaid){
		this.averagePrice = (averagePrice*numberOfTransactions + pricePaid)/(numberOfTransactions+1);
		this.numberOfTransactions++;		
	}
	
	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}
	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}
	public double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

}
