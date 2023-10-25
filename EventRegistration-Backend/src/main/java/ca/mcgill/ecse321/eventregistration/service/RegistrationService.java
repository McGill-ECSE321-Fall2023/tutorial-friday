package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.Registration.RegistrationId;
import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;
import jakarta.transaction.Transactional;

@Service
public class RegistrationService {
	@Autowired
	private PersonService personService;
	@Autowired
	private EventService eventService;
	@Autowired
	private RegistrationRepository registrationRepo;
	
	@Transactional
	public Registration createRegistration(int personId, int eventId) {
		Person person = personService.readPersonById(personId);
		Event event = eventService.readEventById(eventId);
		RegistrationId id = new RegistrationId(person, event);
		Registration registration = new Registration(id);
		return registrationRepo.save(registration);
	}
}
