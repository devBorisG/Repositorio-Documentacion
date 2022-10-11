package edu.uco.carpooling.domain;

import static edu.uco.carpooling.domain.builder.CustomerDTOBuilder.getCustomerDTOBuilder;

public class Prueba {
	public static void main(String[] args) {
		CustomerDTO myCuystomer = getCustomerDTOBuilder().build();
		
		System.out.println(myCuystomer.getDni()+"\n"
					+myCuystomer.getEmailCompany()+"\n"
					+myCuystomer.getId()
				);
	}
}
