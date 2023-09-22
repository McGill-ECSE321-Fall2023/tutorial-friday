package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;

@Entity
public class OnlineEvent extends Event {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// OnlineEvent Attributes
	private String url;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	// Hibernate needs a default constructor to be able to create the object, but it
	// doesn't need to be public
	@SuppressWarnings("unused")
	private OnlineEvent() {
	}

	public OnlineEvent(String aName, Date aDate, Time aStartTime, Time aEndTime, String aUrl) {
		super(aName, aDate, aStartTime, aEndTime);
		url = aUrl;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setUrl(String aUrl) {
		boolean wasSet = false;
		url = aUrl;
		wasSet = true;
		return wasSet;
	}

	public String getUrl() {
		return url;
	}

	public void delete() {
		super.delete();
	}

	public String toString() {
		return super.toString() + "[" + "url" + ":" + getUrl() + "]";
	}
}