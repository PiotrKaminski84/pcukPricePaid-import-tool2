package pl.pmk.bussines;

public class PropertyDetails {
	
	public static final int DETACHED = 1;
	public static final int SEMI_DETACHED = 2;
	public static final int TERRACED = 3;
	public static final int FLAT = 4;
	public static final int OTHER = 5;
	
	private int id;
	private String postCode;
	private int propertyType;
	private String PAON;
	private String SAON;
	private String street;
	private String locality;
	private String town;
	private String country;
	
	
	public PropertyDetails(String postCode, int propertyType, String pAON, String sAON, String street, String locality,
			String town, String country) {
		super();
		this.postCode = postCode;
		this.propertyType = propertyType;
		PAON = pAON;
		SAON = sAON;
		this.street = street;
		this.locality = locality;
		this.town = town;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}
	public String getPAON() {
		return PAON;
	}
	public void setPAON(String pAON) {
		PAON = pAON;
	}
	public String getSAON() {
		return SAON;
	}
	public void setSAON(String sAON) {
		SAON = sAON;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static int getDetached() {
		return DETACHED;
	}
	public static int getSemiDetached() {
		return SEMI_DETACHED;
	}
	public static int getTerraced() {
		return TERRACED;
	}
	public static int getFlat() {
		return FLAT;
	}
	public static int getOther() {
		return OTHER;
	}
	
	


}
