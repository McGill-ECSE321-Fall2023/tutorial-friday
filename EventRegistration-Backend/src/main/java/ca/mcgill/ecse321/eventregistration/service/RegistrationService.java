package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;
import jakarta.transaction.Transactional;

@Service
public class RegistrationService {
	@Autowired
	private RegistrationRepository registrationRepo;
	
	@Transactional
	public Registration createRegistration(Registration registration) {
		return registrationRepo.save(registration);
	}
}
