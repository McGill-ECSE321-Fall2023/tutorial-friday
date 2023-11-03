package ca.mcgill.ecse321.eventregistration.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorDto {
	private List<String> errors;
	
	public ErrorDto(List<String> errors) {
		this.errors = new ArrayList<String>();
		for (String e : errors) {
			this.errors.add(e);
		}
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
}
