package edu.uco.budget.domain;

import edu.uco.budget.crosscutting.helper.UUIDHelper;

import static edu.uco.budget.domain.builder.YearDTOBuilder.getYearDTOBuilder;
import static edu.uco.budget.domain.builder.PersonDTOBuilder.getPersonDTOBuilder;
import static edu.uco.budget.domain.builder.BudgetDTOBuilder.getBudgetDTOBuilder;

public class prueba {
	public static void main(String[] args) {
		YearDTO myYear = getYearDTOBuilder()
				.setId(UUIDHelper.getNewUUID())
				.setYearNumber((short) 2022)
				.build();
		
		PersonDTO myPerson = getPersonDTOBuilder()
				.setId(UUIDHelper.getNewUUID())
				.setIdCard("asd")
				.setFirstName("David")
				.setFirstSurname("Valencia ")
				.build();
		
		BudgetDTO myBudet = getBudgetDTOBuilder()
				.setPerson(myPerson)
				.setYear(myYear)
				.build();
		
		
		System.out.println(myBudet.getPerson().getFirstSurname()+myBudet.getYear().getYearNumber());
		System.out.println(myBudet.getYear().getYearNumber());
	}
}
