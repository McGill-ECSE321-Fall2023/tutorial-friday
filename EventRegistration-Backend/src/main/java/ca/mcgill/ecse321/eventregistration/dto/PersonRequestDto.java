package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonRequestDto {
	private String name;
	private String password;
	
	public PersonRequestDto(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public Person toModel() {
		return new Person(this.name, this.password, false);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
