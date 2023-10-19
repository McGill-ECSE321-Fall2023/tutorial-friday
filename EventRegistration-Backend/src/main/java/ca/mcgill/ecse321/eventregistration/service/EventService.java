package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return this.eventRepo.findEventById(id);
	}
}
