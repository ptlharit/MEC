
public class CarGPS {
	private double lon;
	private double lat;
		
	public CarGPS() {}
	
	public void setLoc(String[] loc){
		this.lon = Double.parseDouble(loc[0]);
		this.lat = Double.parseDouble(loc[1]);
	}
		
	@Override
	public String toString(){
		return lon+","+lat;
	}
}
