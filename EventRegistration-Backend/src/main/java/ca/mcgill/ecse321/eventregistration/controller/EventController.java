package ca.mcgill.ecse321.eventregistration.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.EventRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.EventResponseDto;
import ca.mcgill.ecse321.eventregistration.dto.EventSummaryDto;
import ca.mcgill.ecse321.eventregistration.dto.MultiEventSummaryDto;
import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.service.EventService;

@RestController
public class EventController {
	@Autowired
	private EventService eventService;
	
	@PostMapping("/event")
	public EventSummaryDto createEvent(@RequestBody EventRequestDto event) {
		Event createdEvent = eventService.createEvent(event.toModel());
		return new EventSummaryDto(createdEvent);
	}
	
	@GetMapping("/event")
	public MultiEventSummaryDto readAllEvents() {
		Iterable<Event> allEvents = eventService.readAllEvents();
		ArrayList<EventSummaryDto> dtos = new ArrayList<EventSummaryDto>();
		for (Event e : allEvents) {
			dtos.add(new EventSummaryDto(e));
		}
		return new MultiEventSummaryDto(dtos);
	}
	
	@GetMapping("/event/{id}")
	public EventResponseDto readEventById(@PathVariable int id) {
		Event event = eventService.readEventById(id);
		return new EventResponseDto(event);
	}
}
