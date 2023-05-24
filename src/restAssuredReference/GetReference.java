package restAssuredReference;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

public class GetReference {

	public static void main(String[] args) {
		
        // Declare base URI
		RestAssured.baseURI="https://reqres.in/";
		
		// Configure response body
		int statusCode=given().header("Content-Type","application/json")
				.when().get("api/users?page=2")
				.then().extract().statusCode();
		
		String responseBody=given().header("Content-Type","application/json")
				.when().get("api/users?page=2")
				.then().extract().response().asString();
		
		System.out.println("Status code is: "+ statusCode + " OK");
		System.out.println(responseBody);
		
		// Fetch Expected result 
		
		int id [] = {7, 8, 9, 10, 11, 12};
	    String[] email = {"michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"};
	    String[] first_name = {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
	    String[] last_name = {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
	    String[] avatar = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg",
	    		           "https://reqres.in/img/faces/9-image.jpg","https://reqres.in/img/faces/10-image.jpg",
	    		           "https://reqres.in/img/faces/11-image.jpg","https://reqres.in/img/faces/12-image.jpg"};
		
		JsonPath jspresponse=new JsonPath(responseBody);
		 int count = jspresponse.getList("data").size();
		 //int count = id.length;
		 //System.out.println(count);
		 //validate each object 
		 for(int i=0;i<count; i++)
		 {
			 // Expected result
			 
			 int exp_id = id[i];
		     String exp_email = email[i];
		     String exp_first_name = first_name[i];
		     String exp_last_name = last_name[i];
		     String exp_avatar = avatar[i];
		     
			 // Parse the response body 
		     int res_id = jspresponse.getInt("data["+i+"].id");
			 String res_email = jspresponse.getString("data["+i+"].email");
			 String res_first_name = jspresponse.getString("data["+i+"].first_name");
			 String res_last_name = jspresponse.getString("data["+i+"].last_name");
			 String res_avatar = jspresponse.getString("data["+i+"].avatar");
			 
			 // Validate the response body parameter 
			 Assert.assertEquals(res_id, exp_id,"ID at index " + i);
	         Assert.assertEquals(res_email, exp_email, "Email at index " + i);
	         Assert.assertEquals(res_first_name, exp_first_name, "Firstname at index " + i);
	         Assert.assertEquals(res_last_name, exp_last_name, "Lastname at index " + i);
	         Assert.assertEquals(res_avatar, exp_avatar, "Avatar at index " + i);
		 }
	}
};
/* 
 * Explanation of line no 18 
given() is used to specify the request details, such as the headers, query parameters, and request body.
In this case, the header "Content-Type" is set to "application/json" using the header() method.
when() is used to specify the HTTP method and the URL to send the request to. 
In this case, an HTTP GET request is sent to the URL "/api/users?page=2".
then() is used to define the expected behavior of the response. 
In this case, we are not defining any expectations on the response, so the method is empty.
extract() is used to extract values from the response. 
In this case, we are extracting the status code of the response using the statusCode() method.
Finally, the status code is stored in the integer variable statusCode.

 Explanation of line no 17
given() sets the initial state of the request, such as headers and parameters.
header("Content-Type","application/json") sets the request header "Content-Type" to "application/json".
when() specifies the HTTP method to be used and the endpoint to be accessed.
get("/api/users?page=2") sends an HTTP GET request to the endpoint "/api/users?page=2".
then() specifies the expected response, such as status code and response body.
extract() extracts the response as a Response object.
response() retrieves the response body as a string.
asString() returns the response body as a string variable named "responseBody".
 
 */ 