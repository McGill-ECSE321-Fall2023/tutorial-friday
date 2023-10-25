package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

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

import ca.mcgill.ecse321.eventregistration.dto.MultiPersonResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
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
		ResponseEntity<PersonResponseDto> response = client.postForEntity("/person", john, PersonResponseDto.class);
		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertTrue(response.getBody().getId() > 0, "Response body should have an ID.");
		assertEquals(PERSON_NAME, response.getBody().getName());
		// The isVerified field in the request should be ignored
		assertFalse(response.getBody().isVerified());
		
		// Save the ID to read later
		this.personId = response.getBody().getId();
	}
	
	@Test
	@Order(2)
	public void testReadPerson() {
		String url = String.format("/person/%d", this.personId);
		ResponseEntity<PersonResponseDto> response = client.getForEntity(url, PersonResponseDto.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(this.personId, response.getBody().getId());
		assertEquals(PERSON_NAME, response.getBody().getName());
		assertFalse(response.getBody().isVerified());
	}
	
	@Test
	@Order(2)
	public void testReadAllPeople() {
		String url = "/person";
		ResponseEntity<MultiPersonResponseDto> response = client.getForEntity(url, MultiPersonResponseDto.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		MultiPersonResponseDto responseBody = response.getBody();
		assertNotNull(responseBody);
		assertNotNull(responseBody.getPeople());
		ArrayList<PersonResponseDto> people = new ArrayList<PersonResponseDto>();
		for (PersonResponseDto prd : responseBody.getPeople()) {
			people.add(prd);
		}
		assertEquals(1, people.size());
		assertNotNull(people.get(0));
		assertEquals(this.personId, people.get(0).getId());
		assertEquals(PERSON_NAME, people.get(0).getName());
		assertFalse(people.get(0).isVerified());
	}
}
