package pl.pmk.bussines;

public class PostCode {

	private int id;
	private String postCode;
	private Double latitude;
	private Double longitude;
	private final static double EARTH_RADIUS = 3961; //<-miles ; use -  6373; for km
	
	public PostCode(String buffer) {
		String[] split = buffer.split(",");
		this.id = Integer.parseInt(split[0]);
		this.postCode = split[1];
		this.latitude = Double.parseDouble(split[2]);
		this.longitude =Double.parseDouble(split[3]);
	}
	
	public double distanceFrom(double lati, double longi){
		lati=lati/180*Math.PI;
		longi=longi/180*Math.PI;
		double latitude = this.latitude/180*Math.PI;
		double longitude = this.longitude/180*Math.PI;
		double deltaLatitude = lati - latitude;
		double deltaLongitude = longi - longitude;
		double a = Math.pow(Math.sin(deltaLatitude/2), 2)+Math.cos(lati)*Math.cos(latitude)*Math.pow(Math.sin(deltaLongitude)/2, 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return EARTH_RADIUS *c;		
	}
	
	@Override
	public String toString() {
		return "PostCode [postCode=" + postCode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}
