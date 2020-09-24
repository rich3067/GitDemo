package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	private static RequestSpecification req;
	private JsonPath jp;
	
	public RequestSpecification requestSpec() throws IOException {
		
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			req = new RequestSpecBuilder()
		        	.setBaseUri(getProperty("baseUrl"))
		        	.addQueryParam("key", "qaclick123")
		        	.addFilter(RequestLoggingFilter.logRequestTo(log))
		        	.addFilter(ResponseLoggingFilter.logResponseTo(log))
		        	.setContentType(ContentType.JSON)
		        	.build();
		}
		return req;
		
	}
	
	public String getProperty(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\RichW\\eclipse-workspace\\APIframeartifact\\src\\test\\java\\resources\\global.properties");
		
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response resp, String key) {
		String response = resp.asString();
		jp = new JsonPath(response);
		return jp.get(key).toString();
	}

}
