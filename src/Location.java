import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.json.*;
import org.apache.commons.io.IOUtils; 


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
}




