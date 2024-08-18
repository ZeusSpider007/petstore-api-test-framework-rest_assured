package api.endpoints;

import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;
import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;



// Created for perform Create, Read, Update, Delete requests to the user API.

public class StoreEndPoints {

		// method created for getting URL's from properties file
		static ResourceBundle getURL()
		{
			ResourceBundle routes= ResourceBundle.getBundle("routes"); // Load properties file  // name of the properties file
			return routes;
		}
	
			//get pet store inventory
		public static Response fetchinventory()
		{
			String getinventory_url=getURL().getString("getpetinventory_url");
			
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				
			.when()
				.get(getinventory_url);
				
			return response;
		}
	
		//post pet order on store
		public static Response createOrder(Store storePayload)
		{
			String postorder_url=getURL().getString("postpet_url");
			
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(storePayload)
			.when()
				.post(postorder_url);
				
			return response;
		}
		
		//get pet order by id
		public static Response readOrderID(int orderid)
		{
			String getorderdetails_url=getURL().getString("getpetbyid_url");
			
			
			Response response=given()
							.pathParam("orderid",orderid)
			.when()
				.get(getorderdetails_url);
				
			return response;
		}
		
		//delete order by id
		
		public static Response deleteOrder(int orderid)
		{
			String deleteorder_url=getURL().getString("deletepetbyid_url");
			
			Response response=given()
							.pathParam("orderid",orderid)
			.when()
				.delete(deleteorder_url);
			
			response.then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("type", equalTo("unknown"))
            .body("message", equalTo(String.valueOf(orderid)));
			
			
			return response;
		}	
				
		public static Response deletedOrderpresence(int orderid)
		{
			String deleteorder_url=getURL().getString("deletepetbyid_url");
			
			Response response=given()
							.pathParam("orderid",orderid)
			.when()
				.get(deleteorder_url);
			
			response.then()
            .statusCode(404)
            .body("code", equalTo(1))
            .body("type", equalTo("error"))
            .body("message", equalTo("Order not found"));
					
			return response;
		}	
}
