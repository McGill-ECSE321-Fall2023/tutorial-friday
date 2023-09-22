package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Registration {

	@EmbeddedId
	private RegistrationId id;

	// Hibernate needs a default constructor to be able to create the object, but it
	// doesn't need to be public
	@SuppressWarnings("unused")
	private Registration() {
	}

	public Registration(Person person, Event event) {
		this.id = new Registration.RegistrationId(person, event);
	}

	public RegistrationId getId() {
		return id;
	}

	// See
	// https://docs.jboss.org/hibernate/orm/6.1/userguide/html_single/Hibernate_User_Guide.html#identifiers-composite-aggregated
	@Embeddable
	public static class RegistrationId implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Person person;
		@ManyToOne
		private Event event;

		// Hibernate needs a default constructor to be able to create the object, but it
		// doesn't need to be public
		@SuppressWarnings("unused")
		private RegistrationId() {
		}

		public RegistrationId(Person person, Event event) {
			if (person == null) {
				throw new IllegalArgumentException("Person cannot be null.");
			}
			if (event == null) {
				throw new IllegalArgumentException("Event cannot be null.");
			}
			this.person = person;
			this.event = event;
		}

		public Person getPerson() {
			return person;
		}

		public Event getEvent() {
			return event;
		}

		// Just use the IDs of the person and event for simplicity.
		// They should be unique anyway.
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof RegistrationId)) {
				return false;
			}
			RegistrationId otherId = (RegistrationId) obj;
			return this.person != null && this.person.getId() == otherId.getPerson().getId() && this.event != null
					&& this.event.getId() == otherId.getEvent().getId();
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.person.getId(), this.event.getId());
		}
	}

}