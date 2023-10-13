package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

// Start the app for real so that we can send requests to it.
// Use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Ensure methods run in a specific order (e.g., POST before GET).
@TestMethodOrder(OrderAnnotation.class)
// By default, a different instance of PersonIntegrationTests is created for
// each method: https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle
@TestInstance(Lifecycle.PER_CLASS)
public class PersonIntegrationTests {
	@Autowired
	private TestRestTemplate client;
	
	private final String PERSON_NAME = "John";
	private int personId;
	
	@Test
	@Order(1)
	public void testCreatePerson() {
		Person john = new Person(PERSON_NAME, "password123", true);
		
		ResponseEntity<Person> response = client.postForEntity("/person", john, Person.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().getId() > 0, "Person must have a positive ID.");
		this.personId = response.getBody().getId();
		assertEquals(PERSON_NAME, response.getBody().getName());
		assertEquals(john.getIsVerified(), response.getBody().getIsVerified());
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
