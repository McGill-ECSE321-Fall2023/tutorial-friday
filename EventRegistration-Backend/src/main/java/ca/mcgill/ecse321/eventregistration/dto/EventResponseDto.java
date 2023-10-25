package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

public class EventResponseDto {
	private int id;
	private boolean isOnline;
	private String name;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String url;
	private String address;

	public EventResponseDto(Event event) {
		this.id = event.getId();
		this.isOnline = event instanceof OnlineEvent;
		this.name = event.getName();
		this.date = event.getDate().toLocalDate();
		this.startTime = event.getStartTime().toLocalTime();
		this.endTime = event.getEndTime().toLocalTime();
		this.url = event instanceof OnlineEvent ? ((OnlineEvent) event).getUrl() : null;
		this.address = event instanceof InPersonEvent ? ((InPersonEvent) event).getAddress() : null;
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

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getAddress() {
		return address;
	}
}
