package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.repository.EventRepository;
import jakarta.transaction.Transactional;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepo;

	@Transactional
	public Event createEvent(Event event) {
		return this.eventRepo.save(event);
	}

	@Transactional
	public Iterable<Event> readAllEvents() {
		return this.eventRepo.findAll();
	}

	@Transactional
	public Event readEventById(int id) {
		Event event = this.eventRepo.findEventById(id);
		if (event == null) {
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, String.format("No event with ID %d.", id));
		}
		return event;
	}
}
