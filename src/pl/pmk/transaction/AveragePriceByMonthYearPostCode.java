package pl.pmk.transaction;

public class AveragePriceByMonthYearPostCode {

	public String postCode;
	public int year;
	public int month;
	public int noTransactions;
	public double averagePrice;
	
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getNoTransactions() {
		return noTransactions;
	}

	public void setNoTransactions(int noTransactions) {
		this.noTransactions = noTransactions;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public AveragePriceByMonthYearPostCode(){
		super();
	}

	public AveragePriceByMonthYearPostCode(String postCode, int year, int month, int noTransactions, double averagePrice) {
		super();
		this.postCode = postCode;
		this.year = year;
		this.month = month;
		this.noTransactions = noTransactions;
		this.averagePrice = averagePrice;
	}
	
	public void update(int noTransactions, double averagePrice) {		
		this.averagePrice = this.averagePrice*this.noTransactions +  averagePrice*noTransactions;
		this.noTransactions += noTransactions;
		this.averagePrice/=this.noTransactions;
	}
}
