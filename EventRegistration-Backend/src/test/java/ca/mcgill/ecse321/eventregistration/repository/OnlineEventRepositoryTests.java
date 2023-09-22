package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

@SpringBootTest
public class OnlineEventRepositoryTests {
	@Autowired
	private OnlineEventRepository repo;

	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadOnlineEvent() {
		OnlineEvent lecture = new OnlineEvent("ECSE 202 lecture", Date.valueOf("2020-09-01"), Time.valueOf("8:35:00"),
				Time.valueOf("9:55:00"), "zoom-link");
		
		lecture = repo.save(lecture);
		
		OnlineEvent lectureFromDb = repo.findEventById(lecture.getId());
		
		assertNotNull(lectureFromDb);
		assertEquals(lecture.getId(), lectureFromDb.getId());
		assertEquals(lecture.getName(), lectureFromDb.getName());
		assertEquals(lecture.getDate(), lectureFromDb.getDate());
		assertEquals(lecture.getStartTime(), lectureFromDb.getStartTime());
		assertEquals(lecture.getEndTime(), lectureFromDb.getEndTime());
		assertEquals(lecture.getUrl(), lectureFromDb.getUrl());
	}
}
