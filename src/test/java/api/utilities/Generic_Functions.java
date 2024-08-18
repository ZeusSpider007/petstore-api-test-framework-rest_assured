package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.github.javafaker.Faker;

public class Generic_Functions {

	//This function returns the ISO8601 Format dates in string format
    public static String generateRandomDateInISO8601() {
	     
        Faker faker = new Faker();
        
        // Generating a random date within a given range
        Date randomDate = faker.date().between(
            new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000), // 1 year ago
            new Date() // today
        );
        
        // Formatting the date to ISO 8601 format with milliseconds and Z for UTC timezone
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        // Returned the formatted date as a string
        return iso8601Format.format(randomDate);
    }
	
	
	
}
