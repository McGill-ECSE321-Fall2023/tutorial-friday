package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

public class InPersonEvent extends Event {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// InPersonEvent Attributes
	private String address;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public InPersonEvent(int aId, String aName, Date aDate, Time aStartTime, Time aEndTime, String aAddress) {
		super(aId, aName, aDate, aStartTime, aEndTime);
		address = aAddress;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setAddress(String aAddress) {
		boolean wasSet = false;
		address = aAddress;
		wasSet = true;
		return wasSet;
	}

	public String getAddress() {
		return address;
	}

	public void delete() {
		super.delete();
	}

	public String toString() {
		return super.toString() + "[" + "address" + ":" + getAddress() + "]";
	}
}