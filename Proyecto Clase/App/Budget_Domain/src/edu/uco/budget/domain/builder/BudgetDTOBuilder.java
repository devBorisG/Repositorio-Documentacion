package edu.uco.budget.domain.builder;

import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import static edu.uco.budget.domain.BudgetDTO.create;

import edu.uco.budget.domain.BudgetDTO;

public class BudgetDTOBuilder {
	
	private PersonDTO person;
	private YearDTO year;
	
	private BudgetDTOBuilder() {
		super();
	}
	
	public static final BudgetDTOBuilder getBudgetDTOBuilder() {
		return new BudgetDTOBuilder();
	}
	
	public final BudgetDTOBuilder setPerson(PersonDTO person) {
		this.person = person;
		return this;
	}
	
	public final BudgetDTOBuilder setYear(YearDTO year) {
		this.year = year;
		return this;
	}
	
	public final BudgetDTO build() {
		return create(person,year);
	}
}
