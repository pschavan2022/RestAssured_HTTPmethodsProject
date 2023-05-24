package restAssuredReference;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class DeleteReference {

	public static void main(String[] args) {
		 // Step 1:Declare BaseURL
        RestAssured.baseURI = "https://reqres.in/";
        
        int statusCode5 = given().header("Content-Type","application/json")
        				.when().delete("/api/users/2")
        				.then().extract().statusCode();
        String responseBody5 = given().header("Content-Type","application/json")
        				.when().delete("/api/users/2")
        				.then().extract().response().asString();
        System.out.println(responseBody5);
        System.out.println("Status code is: "+ statusCode5 + " No Content");
        
        Assert.assertEquals(statusCode5, 204);
	}
	
};
