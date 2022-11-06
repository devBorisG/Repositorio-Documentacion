package edu.uco.carpooling.domain;

import static edu.uco.carpooling.domain.builder.CustomerDTOBuilder.getUserDTOBuilder;

public class Prueba {
	public static void main(String[] args) {
		
		CustomerDTO myUser = getUserDTOBuilder().setReferencePoint("La Ceja").setCompanyEmail("David.andres.2801@gmail.com").build();
		
		System.out.println("\n"+myUser.getDni()+"\n"
				+myUser.getFirstSurname()+"\n"
				+myUser.getCompanyEmail()+"\n"
				+myUser.getId()+"\n"
				+myUser.getReferencePoint()
			);
	}
	
}
