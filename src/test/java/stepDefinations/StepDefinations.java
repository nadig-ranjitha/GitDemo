package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import groovyjarjarantlr.Utils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.Utilis;

public class StepDefinations extends Utilis {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data= new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	  res=given().spec(requestSpecification())
	  .body(data.addPlacePayLoad(name,language,address));
	 
    }

	@When("user calls {string} with {string} http request")
    public void user_calls_with_Post_http_request(String resource, String method) {
		response= res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
    }

	@Then("the API call got success with status code {int}")
    public void the_api_call_is_success_with_status_code_200() {
   assertEquals(response.getStatusCode(),200);  
    }

	@Then("{string} in response body is {string}")
    public void in_repsonse_body_is(String KeyValue, String ExpectedValue) {
    	String resp=response.asString();
    	JsonPath js= new JsonPath(resp);
    	assertEquals(js.get(KeyValue).toString(),ExpectedValue);
    }
}

