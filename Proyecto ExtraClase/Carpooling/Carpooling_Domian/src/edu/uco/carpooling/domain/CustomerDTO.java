package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class CustomerDTO extends UserDTO {

	private String referencePoint;

	public CustomerDTO() {
		setReferencePoint(EMPTY);
	}

	public CustomerDTO(final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail) {
		super(id, dni, firstName, secondName, firstSurname, secondSurname, password, phone, companyEmail);
	}
	
	public static final CustomerDTO create (final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail) {
		return new CustomerDTO(
					id,
					dni,
					firstName,
					secondName,
					firstSurname,
					secondSurname,
					password,
					phone,
					companyEmail
				);
	}
	
	public static final CustomerDTO create (final String id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail) {
		return new CustomerDTO(getUUIDFromString(id),dni,firstName,secondName,firstSurname,secondSurname,password,phone,companyEmail);
	}

	public String getReferencePoint() {
		return referencePoint;
	}

	public final void setReferencePoint(final String referencePoint) {
		this.referencePoint = applyTrim(referencePoint);
	}
	
}
