package ca.mcgill.ecse321.eventregistration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Person Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String password;
	private boolean isVerified;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	// Hibernate needs a default constructor, but it doesn't need to be public!
	@SuppressWarnings("unused")
	private Person() {
	}

	public Person(String aName, String aPassword, boolean aIsVerified) {
		name = aName;
		password = aPassword;
		isVerified = aIsVerified;
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

	public boolean setPassword(String aPassword) {
		boolean wasSet = false;
		password = aPassword;
		wasSet = true;
		return wasSet;
	}

	public boolean setIsVerified(boolean aIsVerified) {
		boolean wasSet = false;
		isVerified = aIsVerified;
		wasSet = true;
		return wasSet;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public boolean getIsVerified() {
		return isVerified;
	}

	/* Code from template attribute_IsBoolean */
	public boolean isIsVerified() {
		return isVerified;
	}

	public void delete() {
	}

	public String toString() {
		return super.toString() + "[" + "id" + ":" + getId() + "," + "name" + ":" + getName() + "," + "password" + ":"
				+ getPassword() + "," + "isVerified" + ":" + getIsVerified() + "]";
	}
}