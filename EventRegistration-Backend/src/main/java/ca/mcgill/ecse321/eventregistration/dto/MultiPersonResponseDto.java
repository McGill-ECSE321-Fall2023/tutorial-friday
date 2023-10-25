package ca.mcgill.ecse321.eventregistration.dto;

public class MultiPersonResponseDto {
	private Iterable<PersonResponseDto> people;
	
	public MultiPersonResponseDto() {}
	
	public MultiPersonResponseDto(Iterable<PersonResponseDto> people) {
		this.people = people;
	}
	
	public Iterable<PersonResponseDto> getPeople() {
		return people;
	}
}
