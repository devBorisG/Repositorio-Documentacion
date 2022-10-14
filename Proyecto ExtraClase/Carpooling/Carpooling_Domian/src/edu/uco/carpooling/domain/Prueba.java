package edu.uco.carpooling.domain;

import static edu.uco.carpooling.domain.builder.UserDTOBuilder.getUserDTOBuilder;

public class Prueba {
	public static void main(String[] args) {
		
		UserDTO myUser = getUserDTOBuilder().setReferencePoint("La Ceja").setEmailCompany("David.andres.2801@gmail.com").build();
		
		System.out.println("\n"+myUser.getDni()+"\n"
				+myUser.getFirstSurname()+"\n"
				+myUser.getEmailCompany()+"\n"
				+myUser.getId()+"\n"
				+myUser.getReferencePoint()
			);
	}
	
}
