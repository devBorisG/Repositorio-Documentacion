package edu.uco.budget.domain;

import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;
import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;

import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getDefaultUUID;

import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class BudgetDTO {
	
	private UUID id;
	private PersonDTO person;
	private YearDTO year;
	
	public BudgetDTO() {
		setId(getDefaultUUID(id));
		setPerson(getPersonDTOBuilder().build());
		setYear(getYearDTOBuilder().build());
	}

	public BudgetDTO(final UUID id, final PersonDTO person,final YearDTO year) {
		setId(id);
		setPerson(person);
		setYear(year);
	}
	

	public static final BudgetDTO create(final UUID id,final PersonDTO person,final YearDTO year) {
		return new BudgetDTO(id, person,year);
	}
	
	public static final BudgetDTO create(final String id,final PersonDTO person,final YearDTO year) {
		return new BudgetDTO(getUUIDFromString(id), person,year);
	}

	public UUID getId() {
		return id;
	}

	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	
	public PersonDTO getPerson() {
		return person;
	}

	public final void setPerson(final PersonDTO person) {
		this.person = getDefaultIfNull(person, getPersonDTOBuilder().build()); 
	}

	public YearDTO getYear() {
		return year;
	}

	public final void setYear(final YearDTO year) {
		this.year = getDefaultIfNull(year, getYearDTOBuilder().build());
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
	
}
