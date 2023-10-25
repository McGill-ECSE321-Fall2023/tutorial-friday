package ca.mcgill.ecse321.eventregistration.exception;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class EventRegistrationExceptionHandler {
	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException exception) {
		ErrorDto body = new ErrorDto(Arrays.asList(exception.getMessage()));
		return new ResponseEntity<ErrorDto>(body, exception.getStatus());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException exception) {
		ArrayList<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
			errors.add(cv.getMessage());
		}
		ErrorDto body = new ErrorDto(errors);
		return new ResponseEntity<ErrorDto>(body, HttpStatus.BAD_REQUEST);
	}
}
