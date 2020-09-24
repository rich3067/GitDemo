package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// write code to give place_id
		// only execute this code when place_id is null
		
		StepDefinitions m = new StepDefinitions();
		
		System.out.println("Hook method beforeScenario() starting");
		if (StepDefinitions.place_id == null) { // verify that place_id is null
			// code to give place_id
			m.add_place_payload_with("Rich", "English", "Maryland");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_Id_created_maps_to_using("Rich", "getPlaceAPI");
			
			System.out.println("Adding the place in Hooks method");
		}
		
		System.out.println("Hook method beforeScenario() complete");
	}
}
