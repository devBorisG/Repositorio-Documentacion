package edu.uco.budget.domain;

import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;
import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;
import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull;

public class BudgetDTO {
	
	private PersonDTO person;
	private YearDTO year;
	
	public BudgetDTO() {
		setPerson(getPersonDTOBuilder().build());
		setYear(getYearDTOBuilder().build());
	}

	public BudgetDTO(final PersonDTO person,final YearDTO year) {
		setPerson(getDefaultIfNull(person, getPersonDTOBuilder().build()));
		setYear(getDefaultIfNull(year, getYearDTOBuilder().build()));
	}

	public static final BudgetDTO create(final PersonDTO person,final YearDTO year) {
		return new BudgetDTO(person,year);
	}
	
	public PersonDTO getPerson() {
		return person;
	}

	public final void setPerson(final PersonDTO person) {
		
		this.person = getPersonDTOBuilder()
				.setId(person.getId())
				.setIdCard(person.getIdCard())
				.setFirstName(person.getFirstName())
				.setSecondName(person.getSecondName())
				.setFirstSurname(person.getFirstSurname())
				.setSecondSurname(person.getSecondSurname())
				.build();
	}

	public YearDTO getYear() {
		return year;
	}

	public final void setYear(final YearDTO year) {
		this.year = getYearDTOBuilder()
				.setId(year.getId())
				.setYearNumber(year.getYearNumber())
				.build();
	}
	
}
