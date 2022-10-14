package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;

public class DriverDTO extends CustomerDTO{
	
	public DriverDTO() {
		super();
	}

	public DriverDTO(final UUID id, final int dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final LocalDate born,
			final int phone, final String emailCompany) {
		super(id, dni, firstName, secondName, firstSurname, secondSurname, password, born, phone, emailCompany);
	}
	
	public static final DriverDTO create(final UUID id, final int dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final LocalDate born,
			final int phone, final String emailCompany) {
		return new DriverDTO(
					id,
					dni,
					firstName,
					secondName,
					firstSurname,
					secondSurname,
					password,
					born,
					phone,
					emailCompany
				);
	}	
}
