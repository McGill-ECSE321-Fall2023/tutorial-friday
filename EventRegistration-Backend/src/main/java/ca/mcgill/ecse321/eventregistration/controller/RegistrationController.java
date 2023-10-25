package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.RegistrationRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.RegistrationResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.service.RegistrationService;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/registration")
	public RegistrationResponseDto createRegistration(@RequestBody RegistrationRequestDto request) {
		Registration registration = registrationService.createRegistration(request.getPersonId(), request.getEventId());
		return new RegistrationResponseDto(registration);
	}
}
