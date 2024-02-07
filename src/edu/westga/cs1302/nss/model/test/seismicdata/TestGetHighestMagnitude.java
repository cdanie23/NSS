package edu.westga.cs1302.nss.model.test.seismicdata;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.SeismicData;

class TestGetHighestMagnitude {

	@Test 
	public void testWhenNoEarthquakes() {
		SeismicData data = new SeismicData();
		
		data.getHighestMagnitude();
		
		assertAll(() -> assertEquals(0, data.size()), 
				  () -> assertEquals(-1, data.getHighestMagnitude()));
	}
	
	@Test
	public void testWithMultipleEarthquakes() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 6.8, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Florida", 4.2, 2, 5.2, 12.0);
		data.add(quake2);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(6.8, data.getHighestMagnitude()));
		
	}
}
