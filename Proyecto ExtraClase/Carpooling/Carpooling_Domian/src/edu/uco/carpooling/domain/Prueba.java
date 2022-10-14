package edu.uco.carpooling.domain;

import static edu.uco.carpooling.domain.builder.CustomerDTOBuilder.getCustomerDTOBuilder;
import static edu.uco.carpooling.domain.builder.UserDTOBuilder.getUserDTOBuilder;

public class Prueba {
	public static void main(String[] args) {
		CustomerDTO myCuystomer = getCustomerDTOBuilder().build();
		
		UserDTO myUser = getUserDTOBuilder().setReferencePoint("La Ceja").setEmailCompany("David.andres.2801@gmail.com").build();
		
		System.out.println(myCuystomer.getDni()+"\n"
					+myCuystomer.getEmailCompany()+"\n"
					+myCuystomer.getFirstName()+"\n"
					+myCuystomer.getId()
				);
		
		System.out.println("\n"+myUser.getDni()+"\n"
				+myUser.getFirstSurname()+"\n"
				+myUser.getEmailCompany()+"\n"
				+myUser.getId()+"\n"
				+myUser.getReferencePoint()
			);
	}
	
}
