package ca.mcgill.ecse321.eventregistration.dto;

import ca.mcgill.ecse321.eventregistration.model.Registration;

public class RegistrationResponseDto {
	private PersonResponseDto person;
	private EventSummaryDto event;
	
	public RegistrationResponseDto(Registration registration) {
		this.person = new PersonResponseDto(registration.getIdentity().getPerson());
		this.event = new EventSummaryDto(registration.getIdentity().getEvent());
	}
	
	public PersonResponseDto getPerson() {
		return person;
	}
	
	public EventSummaryDto getEvent() {
		return event;
	}
}
