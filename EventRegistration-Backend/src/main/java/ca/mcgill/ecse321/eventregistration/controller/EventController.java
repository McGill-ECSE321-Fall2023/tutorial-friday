package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;
import ca.mcgill.ecse321.eventregistration.service.EventService;

@RestController
public class EventController {
	@Autowired
	private EventService eventService;
	
	@PostMapping("/event")
	public Event createEvent(@RequestBody OnlineEvent event) {
		return eventService.createEvent(event);
	}
	
	@GetMapping("/event")
	public Iterable<Event> readAllEvents() {
		return eventService.readAllEvents();
	}
	
	@GetMapping("/event/{id}")
	public Event readEventById(@PathVariable int id) {
		return eventService.readEventById(id);
	}
}
