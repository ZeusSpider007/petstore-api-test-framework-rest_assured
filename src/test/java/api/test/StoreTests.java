package api.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndPoints;
import api.payload.Store;
import api.utilities.Generic_Functions;
import io.restassured.response.Response;

public class StoreTests {

	Faker faker;
	Store storePayload;
	
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		storePayload=new Store();
		
		storePayload.setId(faker.number().numberBetween(0, 9));
		storePayload.setPetid(faker.number().numberBetween(0, 9));
		storePayload.setQuantity(faker.number().numberBetween(0, 9));
		storePayload.setShipDate(Generic_Functions.generateRandomDateInISO8601());
		storePayload.setStatus("Placed");
		storePayload.setComplete(true);
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	
	@Test(priority=5)
	public void testGetStoreInventory()
	{
		logger.info("********** Fetching Inventory  ***************");
		Response response=StoreEndPoints.fetchinventory();
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**********Inventory Fetched ***************");
			
	}
	
	@Test(priority=6)
	public void testCreateOrderPet()
	{
		logger.info("********** Creating user  ***************");
		Response response=StoreEndPoints.createOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User is creatged  ***************");
			
	}
	
	@Test(priority=7)
	public void testGetOrderDetailsById()
	{
		logger.info("********** Reading Order Info ***************");
		
		Response response=StoreEndPoints.readOrderID(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********Order Info is displayed ***************");
		
	}
	
	
	@Test(priority=8)
	public void testDeleteOrderById()
	{
		logger.info("**********   Deleting Order  ***************");
		
		Response response=StoreEndPoints.deleteOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("********** Order deleted ***************");
	}
	
	
	@Test(priority=9)
	public void testDeletedOrderPresence()
	{
		logger.info("**********   Checking Deleted Order  ***************");
		
		Response response=StoreEndPoints.deletedOrderpresence(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(),404);
		
		logger.info("********** Order does not exists ***************");
	}
	
	
	
	
	
}
