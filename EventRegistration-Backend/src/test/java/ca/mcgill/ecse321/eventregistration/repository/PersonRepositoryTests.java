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
		repo.deleteAll();
	}
	
	@Test
	public void testCreateAndReadPerson() {
		Person alice = new Person("Alice", "alice-password", false);
		
		alice = repo.save(alice);
		
		Person aliceFromDb = repo.findPersonById(alice.getId());
		
		assertNotNull(aliceFromDb);
		assertEquals(alice.getId(), aliceFromDb.getId());
		assertEquals(alice.getName(), aliceFromDb.getName());
		assertEquals(alice.getPassword(), aliceFromDb.getPassword());
		assertEquals(alice.getIsVerified(), aliceFromDb.getIsVerified());
	}
}
