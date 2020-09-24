package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import static org.junit.Assert.*;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions extends Utils {
	RequestSpecification tempReq;
	//Utils req = new Utils();
	TestDataBuild testData = new TestDataBuild();
	Response res;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String newName, String newLang, String newAddr) throws IOException {

		System.out.println("Adding new location: " + newName + ", " + newLang + ", " + newAddr);
		System.out.println("Repeating new location: " + newName + ", " + newLang + ", " + newAddr);
		System.out.println("Repeating new location again: " + newName + ", " + newLang + ", " + newAddr);
		
		tempReq = given()
			.spec(requestSpec())
			.body(testData.addPlacePayload(newName, newLang, newAddr));

	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		
		tempReq = given()
			.spec(requestSpec())
			.body(testData.deletePlacePayload(place_id));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		
		APIResources apiRes = APIResources.valueOf(resource);
		
		System.out.println("Getting resource from apiRes: " + apiRes.getResource());
		System.out.println("Getting resource from apiRes: " + apiRes.getResource());
		System.out.println("Getting resource from apiRes: " + apiRes.getResource());
		
		if (httpMethod.equalsIgnoreCase("POST")) {
			System.out.println("POST: Setting enum class apiRes to: " + resource);
			res = tempReq.when()
					.post(apiRes.getResource());
			System.out.println("\nResponse in string format: " + res.asString());
		}
		else if (httpMethod.equalsIgnoreCase("GET")) {
			System.out.println("GET: Setting enum class apiRes to: " + resource);
			res = tempReq.when()
					.get(apiRes.getResource());
			System.out.println("\nResponse in string format: " + res.asString());
		}
		
		System.out.println();
	}
	
	@Then("the API call got success with Status Code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(res.getStatusCode(),200);
	    
	}
	
	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(res,keyValue),expectedValue);
		
	}

	@And("verify place-Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(res,"place_id");
		
		tempReq = given()
				.spec(requestSpec())
				.queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(res,"name");
		assertEquals(actualName, expectedName);
	}

}
