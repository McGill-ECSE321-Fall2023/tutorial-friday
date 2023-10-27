package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepo;

	@Transactional
	public Person createPerson(@Valid Person person) {
		return personRepo.save(person);
	}

	@Transactional
	public Iterable<Person> readAllPeople() {
		return personRepo.findAll();
	}

	@Transactional
	public Person readPersonById(int id) {
		Person personFromDb = personRepo.findPersonById(id);
		if (personFromDb == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, String.format("No person with ID %d.", id));
		}
		return personFromDb;
	}
}
