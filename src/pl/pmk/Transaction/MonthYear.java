package pl.pmk.Transaction;

public class MonthYear {
	
	private int month;
	private int year;
	
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
		MonthYear other = (MonthYear) obj;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	public MonthYear(){};
	public MonthYear(int month, int year) {
		super();
		this.month = month;
		this.year = year;
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
