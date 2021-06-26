package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinations sp=new StepDefinations();
		
		
		sp.add_Place_Payload_with("Ranju", "English", "UK");
		sp.user_calls_with_Post_http_request("AddPlaceAPI", "POST");
		

	}

}
