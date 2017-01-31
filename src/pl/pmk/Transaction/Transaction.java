package pl.pmk.transaction;

import java.io.Serializable;
import java.sql.Date;

public class Transaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String postcode;		
	private String pAON;		
	private String sAON;		
	private String street;		
	private String locality;		
	private String town;		
	private String district;		
	private String county;		
	private String id;		
	private Date dateOfTransfer;
	private char tennure;
	private char oldNew;
	private int pricePaid;
	private int propertyType;
	
	public static final int DETACHED = 1;
	public static final int SEMI_DETACHED = 2;
	public static final int TERRACED = 3;
	public static final int FLAT = 4;
	public static final int OTHER = 5;
	
	public Transaction(String postcode, String pAON, String sAON, String street, String locality, String town,
			String district, String county, String id, Date dateOfTransfer, char tennure, char oldNew,
			int pricePaid) {
		super();
		this.postcode = postcode;
		this.pAON = pAON;
		this.sAON = sAON;
		this.street = street;
		this.locality = locality;
		this.town = town;
		this.district = district;
		this.county = county;
		this.id = id;
		this.dateOfTransfer = dateOfTransfer;
		this.tennure = tennure;
		this.oldNew = oldNew;
		this.pricePaid = pricePaid;
	}
	
	public Transaction (String str) {
		str=str.replaceAll("'", "");
		String[] split =str.split(",");	
		switch (split[4].substring(1,1)){
		case "D" :
			this.propertyType = DETACHED;
			break;
		case "S" :
			this.propertyType = SEMI_DETACHED;
			break;
		case "T" :
			this.propertyType = TERRACED;
			break;
		case "F" :
			this.propertyType = FLAT;
			break;
		default:
			this.propertyType = OTHER;
		}
		this.postcode = split[3];
		this.postcode = postcode.replaceAll("\"","");
		this.pAON = split[7];
		this.pAON=pAON.replaceAll("\"","");
		this.sAON = split[8];
		this.sAON=sAON.replaceAll("\"","");
		this.street = split[9];
		this.street=street.replaceAll("\"","");
		this.locality = split[10];
		this.locality=locality.replaceAll("\"","");
		this.town = split[11];
		this.town=town.replaceAll("\"","");
		this.district = split[12];
		this.district=district.replaceAll("\"","");
		this.county = split[13];
		this.county=county.replaceAll("\"","");				
		this.id = split[0].replace("{", "");
		this.id=id.replace("}", "");
		String date = split[2].substring(0, split[2].indexOf(' '));
		this.dateOfTransfer = Date.valueOf(date);
		this.tennure = split[6].charAt(1);
		this.oldNew = split[5].charAt(1);
		this.pricePaid = Integer.parseInt(split[1]);
	}
	
	@Override
	public String toString() {
		return "Transaction [postcode=" + postcode + ", pAON=" + pAON + ", sAON=" + sAON + ", street=" + street
				+ ", locality=" + locality + ", town=" + town + ", district=" + district + ", county=" + county
				+ ", id=" + id + ", dateOfTransfer=" + dateOfTransfer + ", tennure=" + tennure + ", oldNew=" + oldNew
				+ ", pricePaid=" + pricePaid + ", propertyType=" + propertyType + "]";
	}

	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getpAON() {
		return pAON;
	}
	public void setpAON(String pAON) {
		this.pAON = pAON;
	}
	public String getsAON() {
		return sAON;
	}
	public void setsAON(String sAON) {
		this.sAON = sAON;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}
	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}
	public char getTennure() {
		return tennure;
	}
	public void setTennure(char tennure) {
		this.tennure = tennure;
	}
	public char getOldNew() {
		return oldNew;
	}
	public void setOldNew(char oldNew) {
		this.oldNew = oldNew;
	}
	public int getPricePaid() {
		return pricePaid;
	}
	public void setPricePaid(int pricePaid) {
		this.pricePaid = pricePaid;
	}
	public int getTransactionYear(){
		@SuppressWarnings("deprecation")
		int year = dateOfTransfer.getYear();
		return year;
	}
	public int getTransactionMonth(){
		@SuppressWarnings("deprecation")
		int month = dateOfTransfer.getMonth();
		return month;
	}

}

