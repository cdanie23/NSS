package edu.westga.cs1302.nss.test.seismicdata;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.SeismicData;

class TestContains {



	@Test
	public void testWhenObjectIsPresent() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake2 = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		
		assertAll( () -> assertEquals(1, data.size()),
				   () -> assertTrue(data.contains(quake2)));
	}
	
	@Test
	public void testWhenObjectIsNotPresent() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 5.2, 2, 5.2, 12.0);
		
		assertAll( () -> assertEquals(1, data.size()),
				   () -> assertFalse(data.contains(quake2)));
	}
		
	
}