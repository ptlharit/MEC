
class CarGPS {
	private double lon;
	private double lat;
		
	public CarGPS() {}
	
	public void setLoc(String lo, String la){
		this.lon = Double.parseDouble(lo);
		this.lat = Double.parseDouble(la);
	}
	
	public double lon() { return this.lon; }
	public double lat() { return this.lat; }
		
	@Override
	public String toString(){
		return lon+","+lat;
	}
}
