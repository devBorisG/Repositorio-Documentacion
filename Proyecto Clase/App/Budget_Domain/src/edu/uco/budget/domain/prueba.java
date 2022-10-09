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
				.setIdCard("1.000.896.432")
				.setFirstName("  David")
				.setFirstSurname("Valencia  ")
				.build();
		
		BudgetDTO myBudet = getBudgetDTOBuilder()
				.setPerson(myPerson)
				.setYear(myYear)
				.build();
				
		System.out.println(
				"Person:"+"\n\n"
				+"\t Id: "+myBudet.getPerson().getId()+"\n"
				+"\t Id Card: "+myBudet.getPerson().getIdCard()+"\n"
				+"\t First Name: "+myBudet.getPerson().getFirstName()+"\n"
				+"\t Second Name: "+myBudet.getPerson().getSecondName()+"\n"
				+"\t First Surname: "+myBudet.getPerson().getFirstSurname()+"\n"
				+"\t Second Surname: "+myBudet.getPerson().getSecondSurname()+"\n\n\n"
				
				+"Year:"+"\n\n"
				+"\t Id: "+myBudet.getYear().getId()+"\n"
				+"\t Year Number: "+myBudet.getYear().getYearNumber()
				);
	}
}
