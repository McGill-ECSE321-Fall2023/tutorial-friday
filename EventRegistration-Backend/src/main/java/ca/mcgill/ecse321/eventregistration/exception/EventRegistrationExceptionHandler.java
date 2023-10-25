package ca.mcgill.ecse321.eventregistration.exception;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;

@ControllerAdvice
public class EventRegistrationExceptionHandler {
	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException exception) {
		ErrorDto body = new ErrorDto(Arrays.asList(exception.getMessage()));
		return new ResponseEntity<ErrorDto>(body, exception.getStatus());
	}
}
