package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;

@SpringBootTest
public class InPersonEventRepositoryTests {
	@Autowired
	private InPersonEventRepository repo;

	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testCreateAndReadInPersonEvent() {
		InPersonEvent laRonde = new InPersonEvent("LaRonde trip", Date.valueOf("2023-09-23"), Time.valueOf("8:35:00"),
				Time.valueOf("20:35:00"), "LaRonde's address");
		
		laRonde = repo.save(laRonde);
		
		InPersonEvent eventFromDb = repo.findInPersonEventById(laRonde.getId());
		
		assertNotNull(eventFromDb);
		assertEquals(laRonde.getId(), eventFromDb.getId());
		assertEquals(laRonde.getName(), eventFromDb.getName());
		assertEquals(laRonde.getDate(), eventFromDb.getDate());
		assertEquals(laRonde.getStartTime(), eventFromDb.getStartTime());
		assertEquals(laRonde.getEndTime(), eventFromDb.getEndTime());
		assertEquals(laRonde.getAddress(), eventFromDb.getAddress());
	}
}
