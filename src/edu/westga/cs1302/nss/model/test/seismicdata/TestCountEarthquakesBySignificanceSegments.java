package edu.westga.cs1302.nss.model.test.seismicdata;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.nss.model.Earthquake;
import edu.westga.cs1302.nss.model.SeismicData;

class TestCountEarthquakesBySignificanceSegments {

	@Test
	public void testWhenOnlyEarthQuakesMatchInTheFirstSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 2, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 5, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 50, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(2, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(0, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheSecondSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 12, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 15, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 50, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(2, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(0, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheThirdSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 22, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 25, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 50, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(2, test[2]), 
				  () -> assertEquals(0, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheFourthSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 32, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 35, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 50, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(2, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInTheFithSegmentOfSegmentRange() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 42, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 45, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 47, 5.2, 12.0);
		data.add(quake2);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(3, data.size()), 
				  () -> assertEquals(0, test[0]),
				  () -> assertEquals(0, test[1]), 
				  () -> assertEquals(0, test[2]), 
				  () -> assertEquals(0, test[3]), 
				  () -> assertEquals(3, test[4]));
	}
	@Test
	public void testWhenOnlyEarthQuakesMatchInAllSegments() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 5, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 15, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 25, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 0.1, 35, 5.2, 12.0);
		data.add(quake3);
		Earthquake quake4 = new Earthquake(dateTime, "Russia", 0.1, 45, 5.2, 12.0);
		data.add(quake4);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(5, data.size()), 
				  () -> assertEquals(1, test[0]),
				  () -> assertEquals(1, test[1]), 
				  () -> assertEquals(1, test[2]), 
				  () -> assertEquals(1, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testWhenNoSeismicData () {
		SeismicData data = new SeismicData();
		
		assertAll(() -> assertEquals(0, data.size()), 
				  () -> assertEquals(null, data.countEarthquakesBySignificanceSegments(50)));
	}
	@Test
	public void testBorderCaseOfAllSegmentMaxes() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 10, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 20, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 30, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 0.1, 40, 5.2, 12.0);
		data.add(quake3);
		Earthquake quake4 = new Earthquake(dateTime, "Russia", 0.1, 50, 5.2, 12.0);
		data.add(quake4);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(5, data.size()), 
				  () -> assertEquals(1, test[0]),
				  () -> assertEquals(1, test[1]), 
				  () -> assertEquals(1, test[2]), 
				  () -> assertEquals(1, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
	@Test
	public void testAllBorderCasesOfSegmentMinimums() {
		SeismicData data = new SeismicData();
		LocalDateTime dateTime = LocalDateTime.now();
		Earthquake quake = new Earthquake(dateTime, "Florida", 0.3, 0, 5.2, 12.0);
		data.add(quake);
		Earthquake quake1 = new Earthquake(dateTime, "New York", 0.2, 11, 5.2, 12.0);
		data.add(quake1);
		Earthquake quake2 = new Earthquake(dateTime, "Texas", 0.1, 21, 5.2, 12.0);
		data.add(quake2);
		Earthquake quake3 = new Earthquake(dateTime, "Japan", 0.1, 31, 5.2, 12.0);
		data.add(quake3);
		Earthquake quake4 = new Earthquake(dateTime, "Russia", 0.1, 41, 5.2, 12.0);
		data.add(quake4);
		
		int [] test = data.countEarthquakesBySignificanceSegments(10);
		
		assertAll(() -> assertEquals(5, data.size()), 
				  () -> assertEquals(1, test[0]),
				  () -> assertEquals(1, test[1]), 
				  () -> assertEquals(1, test[2]), 
				  () -> assertEquals(1, test[3]), 
				  () -> assertEquals(1, test[4]));
	}
}
