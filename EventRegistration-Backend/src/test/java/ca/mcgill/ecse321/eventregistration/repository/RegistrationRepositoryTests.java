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
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;

@SpringBootTest
public class RegistrationRepositoryTests {

	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private RegistrationRepository registrationRepo;

	@AfterEach
	public void clearDatabase() {
		this.registrationRepo.deleteAll();
		this.personRepo.deleteAll();
		this.eventRepo.deleteAll();
	}

	@Test
	public void testCreateAndReadRegistration() {
		Person alice = new Person("Alice", "alice-password", false);
		alice = personRepo.save(alice);

		InPersonEvent laRonde = new InPersonEvent("LaRonde trip", Date.valueOf("2023-09-23"), Time.valueOf("8:35:00"),
				Time.valueOf("20:35:00"), "LaRonde's address");
		laRonde = eventRepo.save(laRonde);
		
		Registration registration = new Registration(alice, laRonde);
		
		registration = registrationRepo.save(registration);
		
		Registration registrationFromDb = registrationRepo.findRegistrationById(registration.getId());
		
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getId());
		assertNotNull(registrationFromDb.getId().getPerson());
		assertNotNull(registrationFromDb.getId().getEvent());
		assertEquals(alice.getId(), registrationFromDb.getId().getPerson().getId());
		assertEquals(laRonde.getId(), registrationFromDb.getId().getEvent().getId());
	}
}
