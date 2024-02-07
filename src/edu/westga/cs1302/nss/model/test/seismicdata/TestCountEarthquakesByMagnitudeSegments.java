package edu.westga.cs1302.nss.model.test.seismicdata;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.SeismicData;


class TestCountEarthquakesByMagnitudeSegments {

	@Test
	public void testWhenOnlyEarthQuakesMatchInTheFirstSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 3, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesByMagnitudeSegments(0.5);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(3, test[0]));
	}
	
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheSecondSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.6, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.7, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 2.0, 3, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesByMagnitudeSegments(0.5);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(2, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(1, test[3]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheThirdSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 1.2, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 1.3, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 2.0, 3, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesByMagnitudeSegments(0.5);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(2, test[2]), 
				  () -> assertEquals(1, test[3]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheFourthSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 1.7, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 1.8, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 2.0, 3, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesByMagnitudeSegments(0.5);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(3, test[3]));
	}
	@Test
	public void testWhenEarthQuakesMatchInAllSegments () {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.7, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 1.3, 3, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 1.67, 3, 5.2, 12.0);
		data.add(quake3);
		
		int [] test = data.countEarthquakesByMagnitudeSegments(0.50);
		
		assertAll(() -> assertEquals(4, data.size()), 
				  () -> assertEquals(1, test[0]),
				  () -> assertEquals(1, test[1]), 
				  () -> assertEquals(1, test[2]), 
				  () -> assertEquals(1, test[3]));
	}
	@Test
	public void testWhenNoSeismicData () {
		SeismicData data = new SeismicData();
		
		assertAll(() -> assertEquals(0, data.size()), 
				  () -> assertEquals(null, data.countEarthquakesByMagnitudeSegments(5)));
	}
	@Test
	public void testBorderCaseOfAllSegmentMaxes() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.5, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 1, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 1.5, 3, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 2, 3, 5.2, 12.0);
		data.add(quake3);
		
		int [] testResults = data.countEarthquakesByMagnitudeSegments(0.50);
		
		assertAll(() -> assertEquals(4, data.size()), 
				  () -> assertEquals(1, testResults[0]), 
				  () -> assertEquals(1, testResults[1]),
				  () -> assertEquals(1, testResults[2]), 
				  () -> assertEquals(1, testResults[3]));
	}
	@Test
	public void testBorderCaseOfAllSegmentMinimums() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.01, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.51, 2, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 1.01, 3, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 1.51, 3, 5.2, 12.0);
		data.add(quake3);
		Earthquake quake4 = new Earthquake(dateTime, "Russia", 2, 3, 5.2, 12.0);
		data.add(quake4);
		
		int [] testResults = data.countEarthquakesByMagnitudeSegments(0.50);
		
		assertAll(() -> assertEquals(5, data.size()), 
				  () -> assertEquals(1, testResults[0]), 
				  () -> assertEquals(1, testResults[1]),
				  () -> assertEquals(1, testResults[2]), 
				  () -> assertEquals(2, testResults[3]));
	}
}
