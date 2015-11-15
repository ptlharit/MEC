
public class CarGPS {
	private double lon;
	private double lat;
		
	public CarGPS() {}
	
	public void setLoc(String lo, String la){
		this.lon = Double.parseDouble(lo);
		this.lat = Double.parseDouble(la);
	}
		
	@Override
	public String toString(){
		return lon+","+lat;
	}
}
