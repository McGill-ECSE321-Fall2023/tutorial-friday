package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepo;
	
	@Transactional
	public Person createPerson(Person person) {
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
			throw new IllegalArgumentException(String.format("No person with ID %d.", id));
		}
		return personFromDb;
	}
}
