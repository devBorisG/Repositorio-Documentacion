package edu.uco.carpooling.domain;

import java.sql.Date;
import java.util.UUID;

public class DriverDTO extends UserDTO{
	
	public DriverDTO() {
		super();
	}

	public DriverDTO(final UUID id, final int dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final Date born,
			final int phone, final String companyEmail) {
		super(id, dni, firstName, secondName, firstSurname, secondSurname, password, born, phone, companyEmail);
	}
	
	public static final DriverDTO create(final UUID id, final int dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final Date born,
			final int phone, final String companyEmail) {
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
					companyEmail
				);
	}	
}
