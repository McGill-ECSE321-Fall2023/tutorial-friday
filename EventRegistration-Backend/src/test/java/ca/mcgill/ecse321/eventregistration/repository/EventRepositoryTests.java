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
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadOnlineEvent() {
		OnlineEvent lecture = new OnlineEvent("ECSE 202 lecture", Date.valueOf("2020-09-01"), Time.valueOf("8:35:00"),
				Time.valueOf("9:55:00"), "zoom-link");
		
		lecture = repo.save(lecture);
		
		Event lectureFromDb = repo.findEventById(lecture.getId());
		
		assertNotNull(lectureFromDb);
		assertEquals(lecture.getId(), lectureFromDb.getId());
		assertEquals(lecture.getName(), lectureFromDb.getName());
		assertEquals(lecture.getDate(), lectureFromDb.getDate());
		assertEquals(lecture.getStartTime(), lectureFromDb.getStartTime());
		assertEquals(lecture.getEndTime(), lectureFromDb.getEndTime());
		assertTrue(lectureFromDb instanceof OnlineEvent, "Event should be an OnlineEvent.");
		OnlineEvent onlineLectureFromDb = (OnlineEvent)lectureFromDb;
		assertEquals(lecture.getUrl(), onlineLectureFromDb.getUrl());
	}
	
	@Test
	public void testCreateAndReadInPersonEvent() {
		InPersonEvent laRonde = new InPersonEvent("LaRonde trip", Date.valueOf("2023-09-23"), Time.valueOf("8:35:00"),
				Time.valueOf("20:35:00"), "LaRonde's address");
		
		laRonde = repo.save(laRonde);
		
		Event eventFromDb = repo.findEventById(laRonde.getId());
		
		assertNotNull(eventFromDb);
		assertEquals(laRonde.getId(), eventFromDb.getId());
		assertEquals(laRonde.getName(), eventFromDb.getName());
		assertEquals(laRonde.getDate(), eventFromDb.getDate());
		assertEquals(laRonde.getStartTime(), eventFromDb.getStartTime());
		assertEquals(laRonde.getEndTime(), eventFromDb.getEndTime());
		assertTrue(eventFromDb instanceof InPersonEvent, "Event should be an InPersonEvent.");
		InPersonEvent inPersonEventFromDb = (InPersonEvent)eventFromDb;
		assertEquals(laRonde.getAddress(), inPersonEventFromDb.getAddress());
	}
}
