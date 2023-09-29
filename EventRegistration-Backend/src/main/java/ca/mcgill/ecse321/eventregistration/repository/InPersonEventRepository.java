package ca.mcgill.ecse321.eventregistration.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.InPersonEvent;

public interface InPersonEventRepository extends CrudRepository<InPersonEvent, Integer> {
	public InPersonEvent findInPersonEventById(int id);
}
