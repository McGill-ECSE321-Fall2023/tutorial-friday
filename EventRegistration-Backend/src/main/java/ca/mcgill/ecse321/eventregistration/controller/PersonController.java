package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person personToCreate) {
		return personService.createPerson(personToCreate);
	}
	
	@GetMapping("/person")
	public Iterable<Person> readAllPeople() {
		return personService.readAllPeople();
	}
	
	@GetMapping("/person/{id}")
	public Person readPersonById(@PathVariable int id) {
		return personService.readPersonById(id);
	}
}
