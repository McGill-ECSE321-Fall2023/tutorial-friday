package ca.mcgill.ecse321.eventregistration.dto;

public class ErrorDto {
	private Iterable<String> errors;
	
	public ErrorDto(Iterable<String> errors) {
		this.errors = errors;
	}
	
	public Iterable<String> getErrors() {
		return errors;
	}
}
