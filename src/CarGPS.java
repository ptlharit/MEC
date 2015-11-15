/// This module is a simulated GPS System on a Car
class CarGPS {
	
	private double lon;
	private double lat;
		
	public CarGPS() {}
	
	///Sets the Location in the Car GPS
	public void setLoc(String lo, String la){
		this.lon = Double.parseDouble(lo);
		this.lat = Double.parseDouble(la);
	}
	
	/// Returns the longitude
	public double lon() { return this.lon; }
	
	/// Returns the latitude
	public double lat() { return this.lat; }
		
	/// Converts the GPS coordinates to a String
	@Override
	public String toString(){
		return lon+","+lat;
	}
}