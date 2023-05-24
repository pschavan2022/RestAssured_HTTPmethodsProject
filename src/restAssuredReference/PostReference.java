package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;

public class PostReference {

    public static void main(String[] args) {

      
        // Step 1: Configure Request Body
           String BaseURI = "https://reqres.in/";
           String requestBody1 = "{\r\n"
                + "    \"name\": \"morpheus\",\r\n"
                + "    \"job\": \"leader\"\r\n"
                + "}";   
        // Step 2: Set the expected results
           
           
           JsonPath jsprequest = new JsonPath(requestBody1);
           String req_name = jsprequest.getString("name");
           String req_job = jsprequest.getString("job");
            
        // Step 3: Declare Base URL
     	  
           RestAssured.baseURI = BaseURI;
           
        // Step 4: Configure Response Body
        int statusCode1=given().header("Content-Type","application/json").body(requestBody1)
        				.when().post("/api/users")
        				.then().extract().statusCode();

        String responseBody1 = given().header("Content-Type","application/json").body(requestBody1)
        					.when().post("/api/users")
        					.then().extract().response().asString();

        // Step 5: Parse the response body
        JsonPath jsp = new JsonPath(responseBody1);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_id = jsp.getString("id");
        String res_createdAt = jsp.getString("createdAt");
        String actualdate = res_createdAt.substring(0,10);
        String currentdate = LocalDate.now().toString(); 
        
        // Step 6: Validate the response body parameters
        Assert.assertEquals(statusCode1, 201);
        Assert.assertEquals(res_name, req_name);
        Assert.assertEquals(res_job, req_job);
        Assert.assertNotNull(res_id);
        Assert.assertEquals(actualdate , currentdate);
                         
    	System.out.println("Status code is: "+ statusCode1 + " Created");
    	// System.out.println(requestBody1);
    	System.out.println(responseBody1);
    	System.out.println(currentdate);  
    }
};