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
		return this.personRepo.save(person);
	}
	
	@Transactional
	public Person readPerson(int id) {
		return this.personRepo.findPersonById(id);
	}
}
