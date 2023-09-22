package ca.mcgill.ecse321.eventregistration.model;

public class Registration {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Registration Associations
	private Person person;
	private Event event;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Registration(Person aPerson, Event aEvent) {
		if (!setPerson(aPerson)) {
			throw new RuntimeException(
					"Unable to create Registration due to aPerson. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		if (!setEvent(aEvent)) {
			throw new RuntimeException(
					"Unable to create Registration due to aEvent. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetOne */
	public Person getPerson() {
		return person;
	}

	/* Code from template association_GetOne */
	public Event getEvent() {
		return event;
	}

	/* Code from template association_SetUnidirectionalOne */
	public boolean setPerson(Person aNewPerson) {
		boolean wasSet = false;
		if (aNewPerson != null) {
			person = aNewPerson;
			wasSet = true;
		}
		return wasSet;
	}

	/* Code from template association_SetUnidirectionalOne */
	public boolean setEvent(Event aNewEvent) {
		boolean wasSet = false;
		if (aNewEvent != null) {
			event = aNewEvent;
			wasSet = true;
		}
		return wasSet;
	}

	public void delete() {
		person = null;
		event = null;
	}

}