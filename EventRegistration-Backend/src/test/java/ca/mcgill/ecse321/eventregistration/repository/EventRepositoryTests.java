package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

@SpringBootTest
public class EventRepositoryTests {
	@Autowired
	private EventRepository repo;
	
	@AfterEach
	public void clearDatabase() {
		this.repo.deleteAll();
	}
	
	@Test
	public void testCreateAndReadOnlineEvent() {
		// Create event
		OnlineEvent moviesEvent = new OnlineEvent("Movies", Date.valueOf("2023-09-30"), Time.valueOf("8:00:00"), Time.valueOf("20:00:00"), "zoom.com");

		// Save in database
		moviesEvent = this.repo.save(moviesEvent);
		
		// Read from database
		Event eventFromDb = this.repo.findEventById(moviesEvent.getId());
		
		// Assert
		assertNotNull(eventFromDb);
		assertEquals(moviesEvent.getId(), eventFromDb.getId());
		assertEquals(moviesEvent.getDate(), eventFromDb.getDate());
		assertEquals(moviesEvent.getStartTime(), eventFromDb.getStartTime());
		assertEquals(moviesEvent.getEndTime(), eventFromDb.getEndTime());
		assertTrue(eventFromDb instanceof OnlineEvent, "The event should be an online event.");
		OnlineEvent moviesEventFromDb = (OnlineEvent)eventFromDb;
		assertEquals(moviesEvent.getUrl(), moviesEventFromDb.getUrl());
	}
	
	@Test
	public void testCreateAndReadInPersonEvent() {
		// Create event
		InPersonEvent moviesEvent = new InPersonEvent("Barbenheimer", Date.valueOf("2023-09-30"), Time.valueOf("8:00:00"), Time.valueOf("20:00:00"), "Cineplex");

		// Save in database
		moviesEvent = this.repo.save(moviesEvent);
		
		// Read from database
		Event eventFromDb = this.repo.findEventById(moviesEvent.getId());
		
		// Assert
		assertNotNull(eventFromDb);
		assertEquals(moviesEvent.getId(), eventFromDb.getId());
		assertEquals(moviesEvent.getDate(), eventFromDb.getDate());
		assertEquals(moviesEvent.getStartTime(), eventFromDb.getStartTime());
		assertEquals(moviesEvent.getEndTime(), eventFromDb.getEndTime());
		assertTrue(eventFromDb instanceof InPersonEvent, "The event should be an in-person event.");
		InPersonEvent moviesEventFromDb = (InPersonEvent)eventFromDb;
		assertEquals(moviesEvent.getAddress(), moviesEventFromDb.getAddress());
	}
}
