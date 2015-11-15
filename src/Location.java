import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.json.*;
import org.apache.commons.io.IOUtils; 

import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class Location {
	
	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; 

	public static String[] getGeoLocation(String fullAddress) throws IOException, JSONException {

		URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8")+ "&sensor=false");
	
		URLConnection conn = url.openConnection();
	
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
	
		IOUtils.copy(conn.getInputStream(), output);
	
		output.close();

		JSONObject jsonObject = new JSONObject(output.toString());
	    String lat = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").toString();
		
	    String[] location = lat.split(",");
	    location[0] = location[0].replace("{\"lng\":","");
	    location[1] = location[1].replace("\"lat\":","");
	    location[1] = location[1].replace("}", "");
	    
	    return location;

	}
	
	public List<Place> getRepairLocations(String make){
		GooglePlaces client = new GooglePlaces("AIzaSyCCUcdjaHg0yPR9HDU1m5sNvtjxYoROFhg");
		List<Place> places = client.getPlacesByQuery(make, GooglePlaces.MAXIMUM_RESULTS, Param.name("radius").value(2000), Param.name("location").value("43.2617486,-79.9227811"));
	
		return places;
	}
	
//	public static void main(String args[]) throws JSONException, IOException{
//		String[] temp = Location.getGeoLocation("McMaster University");
//		System.out.println(temp[0] + ", " + temp[1]);
//	}

}