package ca.mcgill.ecse321.eventregistration.dto;

public class MultiEventSummaryDto {
	private Iterable<EventSummaryDto> eventSummaries;
	
	public MultiEventSummaryDto() {}
	
	public MultiEventSummaryDto(Iterable<EventSummaryDto> eventSummaries) {
		this.eventSummaries = eventSummaries;
	}
	
	public Iterable<EventSummaryDto> getEventSummaries() {
		return eventSummaries;
	}
}
