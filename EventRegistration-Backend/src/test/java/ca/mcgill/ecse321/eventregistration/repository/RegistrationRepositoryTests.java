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
import ca.mcgill.ecse321.eventregistration.model.Registration.RegistrationId;

@SpringBootTest
public class RegistrationRepositoryTests {
	@Autowired
	private RegistrationRepository registrationRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private EventRepository eventRepo;
	
	@AfterEach
	public void clearDatabase() {
		this.registrationRepo.deleteAll();
		this.personRepo.deleteAll();
		this.eventRepo.deleteAll();
	}
	
	@Test
	public void createAndReadRegistration() {
		Person john = new Person("John Doe", "johns-password", false);
		john = this.personRepo.save(john);
		
		InPersonEvent moviesEvent = new InPersonEvent("Barbenheimer", Date.valueOf("2023-09-30"), Time.valueOf("8:00:00"), Time.valueOf("20:00:00"), "Cineplex");
		moviesEvent = this.eventRepo.save(moviesEvent);
		
		RegistrationId registrationId = new RegistrationId(john, moviesEvent);
		Registration registration = new Registration(registrationId);
		registration = this.registrationRepo.save(registration);
		
		Registration registrationFromDb = this.registrationRepo.findRegistrationByIdentity(registrationId);
		
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getIdentity());
		assertNotNull(registrationFromDb.getIdentity().getPerson());
		assertEquals(john.getId(), registrationFromDb.getIdentity().getPerson().getId());
		assertNotNull(registrationFromDb.getIdentity().getEvent());
		assertEquals(moviesEvent.getId(), registrationFromDb.getIdentity().getEvent().getId());
	}
}
