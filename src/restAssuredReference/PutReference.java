package restAssuredReference;

import org.testng.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;

public class PutReference {

	public static void main(String[] args) {
        
        // Step 1: Configure Request Body & request body variables
        String BaseURI = "https://reqres.in/";
        String requestBody = "{\r\n"
        		+ "    \"name\": \"morpheus\",\r\n"
        		+ "    \"job\": \"zion resident\"\r\n"
        		+ "}";   
        // Step 2: Fetch request body parameter values
        
        JsonPath jsprequest = new JsonPath(requestBody);
        String req_name = jsprequest.getString("name");
        String req_job = jsprequest.getString("job");
		
        // Step 3:Declare BaseURL
        RestAssured.baseURI = BaseURI ;
        
        // Step 4:Configure Request Body
        
        System.out.println(requestBody);
        int statusCode = given().header("Content-Type","application/json").body(requestBody)
        				.when().put("/api/users/2")
        				.then().extract().statusCode();
             
        String responseBody = given().header("Content-Type","application/json").body(requestBody)
        					.when().put("/api/users/2")
        					.then().extract().response().asString();
        System.out.println(responseBody);
        
        //Parse the response body
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
        String res_job = jsp.getString("job");
        String res_updatedAt = jsp.getString("updatedAt");       
        String actualdate = res_updatedAt.substring(0,10);
        String currentdate = LocalDate.now().toString(); 
        
        /*  System.out.println("Status code is: "+ statusCode1 + " OK");  
        	System.out.println("name : "+res_name1);
            System.out.println("job : "+res_job1);
            System.out.println("updatedAt : "+res_updatedAt1); */
        
        //Validate response body
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(res_name , req_name);
        Assert.assertEquals(res_job , req_job);
        Assert.assertEquals(actualdate , currentdate);

    }
};