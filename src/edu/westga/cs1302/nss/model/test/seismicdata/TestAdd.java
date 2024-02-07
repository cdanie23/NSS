package edu.westga.cs1302.nss.model.test.seismicdata;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.SeismicData;

class TestAdd {

	@Test
	public void testWhenAddingDuplicate() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake2 = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		
		assertAll( () -> assertEquals(1, data.size()),
				   () -> assertThrows(IllegalArgumentException.class, () ->
				                      data.add(quake2)),
				   () -> assertEquals(1, data.size()));
	}
	
	@Test
	public void testWhenAddingNonDuplicate() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 5.2, 2, 5.2, 12.0);
		
		assertAll(() -> assertEquals(0, data.size()), 
				  () -> assertTrue(data.add(quake)),
				  () -> assertEquals(1, data.size()));
	}

}
