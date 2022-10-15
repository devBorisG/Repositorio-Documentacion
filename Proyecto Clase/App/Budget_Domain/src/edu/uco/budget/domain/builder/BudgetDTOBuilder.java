package edu.uco.budget.domain.builder;

import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import static edu.uco.budget.domain.BudgetDTO.create;

import java.util.UUID;

import edu.uco.budget.domain.BudgetDTO;

public class BudgetDTOBuilder {
	
	private UUID id;
	private PersonDTO person;
	private YearDTO year;
	
	private BudgetDTOBuilder() {
		super();
	}
	
	public static final BudgetDTOBuilder getBudgetDTOBuilder() {
		return new BudgetDTOBuilder();
	}
	
	
	public final BudgetDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
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
		return create(id, person,year);
	}
}
