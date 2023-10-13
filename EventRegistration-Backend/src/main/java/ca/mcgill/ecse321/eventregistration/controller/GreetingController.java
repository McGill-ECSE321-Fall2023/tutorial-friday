package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	// Reach this endpoint with
	// GET /hello/Alice or GET /hello/Alice?name=Bob
	// but *not* GET /hello/Alice/ (notice the trailing slash).
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name, @RequestParam(defaultValue = "") String friend) {
		String response = String.format("Hello there, %s", name);
		if (friend != null && friend.trim().length() > 0) {
			response += String.format(" and %s", friend);
		}
		response += "!";
		return response;
	}
}
