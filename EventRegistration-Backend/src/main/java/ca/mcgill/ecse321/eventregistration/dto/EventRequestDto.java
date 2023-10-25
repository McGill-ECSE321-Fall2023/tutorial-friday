package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;
import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

public class EventRequestDto {
	private boolean isOnline;
	private String name;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String url;
	private String address;

	public Event toModel() {
		return this.isOnline
				? new OnlineEvent(this.name, Date.valueOf(this.date), Time.valueOf(this.startTime),
						Time.valueOf(this.endTime), this.url)
				: new InPersonEvent(this.name, Date.valueOf(this.date), Time.valueOf(this.startTime),
						Time.valueOf(this.endTime), this.address);
	}
	
	// If these setters are missing, then for some reason Jackson fails to
	// deserialize the date and times

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
