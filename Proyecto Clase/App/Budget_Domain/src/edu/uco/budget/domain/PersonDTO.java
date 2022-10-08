package edu.uco.budget.domain;

import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.budget.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.budget.crosscutting.helper.StringHelper.EMPTY;

public class PersonDTO {
	
	private UUID id;
	private String idCard;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	
	private PersonDTO() {
		setId(getNewUUID());
		setIdCard(EMPTY);
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
	}

	public PersonDTO(UUID id, String idCard, String firstName, String secondName, String firstSurname, String secondSurname) {
		this.id = id;
		this.idCard = idCard;
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstSurname = firstSurname;
		this.secondSurname = secondSurname;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(final String idCard) {
		this.idCard = applyTrim(idCard);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = applyTrim(firstName);
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(final String secondName) {
		this.secondName = applyTrim(secondName);
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(final String firstSurname) {
		this.firstSurname = applyTrim(firstSurname);
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(final String secondSurname) {
		this.secondSurname = applyTrim(secondSurname);
	}
		
}
