package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

public class EventSummaryDto {
	private int id;
	private boolean isOnline;
	private String name;
	private LocalDate date;
	
	public EventSummaryDto(Event event) {
		this.id = event.getId();
		this.isOnline = event instanceof OnlineEvent;
		this.name = event.getName();
		this.date = event.getDate().toLocalDate();
	}
	
	public int getId() {
		return id;
	}

	public boolean isOnline() {
		return isOnline;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getDate() {
		return date;
	}
}
