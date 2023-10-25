package ca.mcgill.ecse321.eventregistration.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.MultiPersonResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	
	/**
	 * Create a person.
	 * 
	 * @param personToCreate The person to create.
	 * @return The created person.
	 */
	@PostMapping("/person")
	public PersonResponseDto createPerson(@RequestBody PersonRequestDto personToCreate) {
		Person personModel = personToCreate.toModel();
		Person createdPerson = personService.createPerson(personModel);
		return new PersonResponseDto(createdPerson);
	}
	
	/**
	 * Find all the people.
	 * 
	 * @return All the people.
	 */
	@GetMapping("/person")
	public MultiPersonResponseDto readAllPeople() {
		Iterable<Person> allPeople = personService.readAllPeople();
		ArrayList<PersonResponseDto> dtos = new ArrayList<PersonResponseDto>();
		for (Person p : allPeople) {
			dtos.add(new PersonResponseDto(p));
		}
		return new MultiPersonResponseDto(dtos);
	}
	
	/**
	 * Find a specific person by ID.
	 * 
	 * @param id The person's primary key.
	 * @return The person.
	 */
	@GetMapping("/person/{id}")
	public PersonResponseDto readPersonById(@PathVariable int id) {
		Person person = personService.readPersonById(id);
		return new PersonResponseDto(person);
	}
}
