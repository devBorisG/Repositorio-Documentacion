package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

public class UserDTO extends CustomerDTO {

	private String referencePoint;

	public UserDTO() {
		super();//funcionara asi???? HACER PRUEBA
		setReferencePoint(EMPTY);
	}

	public UserDTO(final UUID id, final int dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final LocalDate born,
			final int phone, final String emailCompany, final String referencePoint) {
		super(id, dni, firstName, secondName, firstSurname, secondSurname, password, born, phone, emailCompany);
		setReferencePoint(referencePoint);
	}

	public String getReferencePoint() {
		return referencePoint;
	}

	public final void setReferencePoint(final String referencePoint) {
		this.referencePoint = applyTrim(referencePoint);
	}
	
}
