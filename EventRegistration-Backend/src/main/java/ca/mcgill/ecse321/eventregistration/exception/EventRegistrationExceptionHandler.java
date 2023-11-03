package ca.mcgill.ecse321.eventregistration.exception;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class EventRegistrationExceptionHandler {

	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException ex) {
		ErrorDto responseBody = new ErrorDto(Arrays.asList(ex.getMessage()));
		return new ResponseEntity<ErrorDto>(responseBody, ex.getStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto handleConstraintViolationException(ConstraintViolationException ex) {
		ArrayList<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
			errorMessages.add(cv.getMessage());
		}
		ErrorDto responseBody = new ErrorDto(errorMessages);
		return responseBody;
	}
}
