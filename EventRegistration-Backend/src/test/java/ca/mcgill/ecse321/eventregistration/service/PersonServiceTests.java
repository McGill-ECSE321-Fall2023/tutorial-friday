package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@SpringBootTest
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PersonServiceTests {

	@Mock
	private PersonRepository personRepo;
	@InjectMocks
	private PersonService personService;

	@Test
	public void testCreateValidPerson() {
		// Setup
		String name = "I Dunno";
		String password = "password";
		Person iDunno = new Person(name, password, false);
		when(personRepo.save(iDunno)).thenReturn(iDunno);
		// Act
		Person response = personService.createPerson(iDunno);
		// Assert
		assertNotNull(response);
		assertEquals(name, response.getName());
		assertEquals(password, response.getPassword());
		assertFalse(response.getIsVerified());
		// personRepo.save() should be called EXACTLY once with input iDunno
		verify(personRepo, times(1)).save(iDunno);
	}

	@Test
	public void testReadByValidId() {
		// Setup
		int validId = 1;
		String name = "I Dunno";
		String password = "password";
		Person cannedResponse = new Person(name, password, false);
		when(personRepo.findPersonById(validId)).thenReturn(cannedResponse);
		// Act
		Person person = personService.readPersonById(validId);
		// Assert
		assertNotNull(person);
		assertEquals(name, person.getName());
		assertEquals(password, person.getPassword());
		assertFalse(person.getIsVerified());
	}

	@Test
	public void testReadByInvalidId() {
		// Setup
		int invalidId = 1;
		// Act and assert
		EventRegistrationException ex = assertThrows(EventRegistrationException.class,
				() -> personService.readPersonById(invalidId));
		assertEquals(String.format("No person with ID %d.", invalidId), ex.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
		// assertThrows is roughly equivalent to the following:
//		try {
//			personService.readPersonById(invalidId);
//			fail("No exception was thrown.");
//		}
//		catch (IllegalArgumentException e) {
//			// More assertions about e
//		}
	}
}
