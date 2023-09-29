package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

	@EmbeddedId
	private RegistrationId identity;

	// Hibernate needs a default constructor, but it doesn't need to be public!
	@SuppressWarnings("unused")
	private Registration() {
	}
	
	public Registration(RegistrationId id) {
		this.identity = id;
	}

	public RegistrationId getIdentity() {
		return identity;
	}

	@Embeddable
	public static class RegistrationId implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		private Person person;
		@ManyToOne
		private Event event;

		public RegistrationId() {
		}

		public RegistrationId(Person person, Event event) {
			this.person = person;
			this.event = event;
		}

		public Event getEvent() {
			return event;
		}

		public Person getPerson() {
			return person;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof RegistrationId)) {
				return false;
			}
			RegistrationId otherId = (RegistrationId) obj;
			return otherId.getPerson() != null && this.person.getId() == otherId.getPerson().getId()
					&& otherId.getEvent() != null && this.event.getId() == otherId.getEvent().getId();
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.person.getId(), this.event.getId());
		}
	}
}
