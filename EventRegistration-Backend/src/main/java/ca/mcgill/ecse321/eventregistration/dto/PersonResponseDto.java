package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {
	private int id;
	private String name;
	private boolean isVerified;
	
	public PersonResponseDto(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.isVerified = person.getIsVerified();
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isVerified() {
		return isVerified;
	}
}
