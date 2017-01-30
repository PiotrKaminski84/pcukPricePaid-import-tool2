package pl.pmk.Transaction;

public class PcMonthYear {
	
	private int month;
	private int year;
	private String postCode;
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		postCode = postCode;
	}
	public int getMonth() {
		return month;
	}
	@Override
	public String toString() {
		return "MonthYear, " + year + ", " + month + ", ";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = 12*month;
		result = year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PcMonthYear other = (PcMonthYear) obj;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		if (postCode != other.getPostCode())
			return false;
		return true;
	}
	public PcMonthYear(){};
	public PcMonthYear(int month, int year, String postCode) {
		super();
		this.month = month;
		this.year = year;
		this.postCode = postCode;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

}
