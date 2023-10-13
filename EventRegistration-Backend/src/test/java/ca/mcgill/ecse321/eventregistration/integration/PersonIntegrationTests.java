package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

// Start the app for real.
// Use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Use the same instance for all tests. By default, JUnit creates a new instance per method.
@TestInstance(Lifecycle.PER_CLASS)
// Run the tests in the order specified by the @Order annotations (e.g., create before read).
@TestMethodOrder(OrderAnnotation.class)
public class PersonIntegrationTests {
	@Autowired
	private TestRestTemplate client;
	@Autowired
	private PersonRepository personRepo;
	
	private int personId;
	private final String PERSON_NAME = "John";
	
	// NOT @AfterEach, otherwise the person created in the POST test will be deleted before the GET test
	@AfterAll
	public void clearDatabase() {
		personRepo.deleteAll();
	}
	
	@Test
	@Order(1)
	public void testCreatePerson() {
		// Write a request
		Person john = new Person(PERSON_NAME, "password123", true);
		// Send the request
		ResponseEntity<Person> response = client.postForEntity("/person", john, Person.class);
		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().getId() > 0, "Response body should have an ID.");
		assertEquals(PERSON_NAME, response.getBody().getName());
		assertTrue(response.getBody().getIsVerified());
		
		// Save the ID to read later
		this.personId = response.getBody().getId();
	}
	
	@Test
	@Order(2)
	public void testReadPerson() {
		String url = String.format("/person/%d", this.personId);
		ResponseEntity<Person> response = client.getForEntity(url, Person.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(this.personId, response.getBody().getId());
		assertEquals(PERSON_NAME, response.getBody().getName());
		assertTrue(response.getBody().getIsVerified());
	}
}
