package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.newAddPlace;
import pojo.newLocation;

public class TestDataBuild {
	
	public newAddPlace addPlacePayload(String name, String language, String address) {
		newAddPlace ap = new newAddPlace();
        newLocation loc = new newLocation();
        
        ap.setName(name);
        ap.setLanguage(language);
        ap.setAddress(address);
        ap.setAccuracy(50);
        //ap.setAddress("29, side layout, cohen 09");
        //ap.setLanguage("French-IN");
        //ap.setPhone_num("(+91) 983 893 3937");
        ap.setPhone_number("987654321");
        //ap.setName("Frontline house");
        ap.setWebsite("http://google.com");
        List<String> myString = new ArrayList<String>();
        myString.add("RequestSpecificationshoe park");
        myString.add("shop");
       
        ap.setTypes(myString);
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);
        
        return ap;
	}

	public String deletePlacePayload(String place_id) {
		
		System.out.println("test string 1");
		System.out.println("test string 2");
		System.out.println("test string 3");
		System.out.println("test string 4");
		System.out.println("test string 5");
		
		
		return "{\r\n    \"place_id\":\"" + place_id + "\"\r\n}";
	}
}
