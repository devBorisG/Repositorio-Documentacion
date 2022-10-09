package edu.uco.budget.domain;

import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;
import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;

public class BudgetDTO {
	
	private PersonDTO person;
	private YearDTO year;
	
	public BudgetDTO() {
		setPerson(person);
		setYear(year);
	}

	public BudgetDTO(final PersonDTO person,final YearDTO year) {
		setPerson(person);
		setYear(year);
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
