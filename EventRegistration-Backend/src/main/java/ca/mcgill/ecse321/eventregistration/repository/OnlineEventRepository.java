package ca.mcgill.ecse321.eventregistration.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.OnlineEvent;

public interface OnlineEventRepository extends CrudRepository<OnlineEvent, Integer> {
	public OnlineEvent findOnlineEventById(int id);
}
