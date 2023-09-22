package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class PersonRepositoryTests {
	@Autowired
	private PersonRepository repo;
	
	@AfterEach
	public void clearDatabase() {
		this.repo.deleteAll();
	}
	
	@Test
	public void testCreateAndReadPerson() {
		// Set up
		Person john = new Person("John Doe", "johns-password", false);
		
		// Act
		john = this.repo.save(john);
		Person johnFromDb = this.repo.findPersonById(john.getId());
		
		// Assertions
		assertNotNull(johnFromDb);
		assertEquals(john.getId(), johnFromDb.getId());
		assertEquals(john.getName(), johnFromDb.getName());
		assertEquals(john.getPassword(), johnFromDb.getPassword());
		assertEquals(john.getIsVerified(), johnFromDb.getIsVerified());
	}
}
