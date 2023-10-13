package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	// Valid requests:
	//  - GET /hello/Alice?friend=Charlie
	//  - GET /hello/Dave
	// Invalid examples:
	//  - GET /hello/
	//  - GET /hello/Alice/            (notice the trailing slash)
	//  - GET /hello/Alice/?friend=Bob (notice the trailing slash)
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name,
			@RequestParam(defaultValue="Bob") String friend) {
		return String.format("Hello, %s and %s!", name, friend);
	}
}
