package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDate;
import org.testng.Assert;

public class PatchReference {

    public static void main(String[] args) {
        // Step 1: Declare BaseURL
        RestAssured.baseURI = "https://reqres.in/";

        // Step 2: Parse the request body
        String requestBody = "{\r\n"
                + "    \"name\": \"morpheus\"\r\n"
                + "}";
        // Set the expected results
        JsonPath jsprequest = new JsonPath(requestBody);
        String req_name = jsprequest.getString("name");
        System.out.println(requestBody);
   
        int statusCode=given().header("Content-Type","application/json").body(requestBody)
        				.when().patch("/api/users/2")
        				.then().extract().statusCode();

        String responseBody = given().header("Content-Type","application/json").body(requestBody)
        						.when().patch("/api/users/2")
        						.then().extract().response().asString();
        System.out.println(responseBody);
        // Parse the response body
        JsonPath jsp = new JsonPath(responseBody);
        String res_name = jsp.getString("name");
	    String res_updatedAt=jsp.getString("updatedAt");
	    String actualdate=res_updatedAt.substring(0,10);
	    String currentdate=LocalDate.now().toString();	
	    //System.out.println(actualdate);
	    //System.out.println(currentdate);
	    
        //Validate response body
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(res_name ,req_name);
        Assert.assertEquals(actualdate,currentdate);

    }
};