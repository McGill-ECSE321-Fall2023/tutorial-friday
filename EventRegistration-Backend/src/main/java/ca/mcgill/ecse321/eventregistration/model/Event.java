package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Event {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Event Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private Date date;
	private Time startTime;
	private Time endTime;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------
	
	// Hibernate needs a default constructor, but it doesn't need to be public!
	@SuppressWarnings("unused")
	protected Event() {
	}

	public Event(String aName, Date aDate, Time aStartTime, Time aEndTime) {
		name = aName;
		date = aDate;
		startTime = aStartTime;
		endTime = aEndTime;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setId(int aId) {
		boolean wasSet = false;
		id = aId;
		wasSet = true;
		return wasSet;
	}

	public boolean setName(String aName) {
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public boolean setDate(Date aDate) {
		boolean wasSet = false;
		date = aDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setStartTime(Time aStartTime) {
		boolean wasSet = false;
		startTime = aStartTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setEndTime(Time aEndTime) {
		boolean wasSet = false;
		endTime = aEndTime;
		wasSet = true;
		return wasSet;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void delete() {
	}

	public String toString() {
		return super.toString() + "[" + "id" + ":" + getId() + "," + "name" + ":" + getName() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "date" + "="
				+ (getDate() != null ? !getDate().equals(this) ? getDate().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "startTime" + "="
				+ (getStartTime() != null
						? !getStartTime().equals(this) ? getStartTime().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "endTime" + "="
				+ (getEndTime() != null
						? !getEndTime().equals(this) ? getEndTime().toString().replaceAll("  ", "    ") : "this"
						: "null");
	}
}