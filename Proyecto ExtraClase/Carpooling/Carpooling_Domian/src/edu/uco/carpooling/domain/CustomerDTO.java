package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;

public class CustomerDTO extends UserDTO {

	public CustomerDTO() {
		setId(getNewUUID());
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPassword(EMPTY);
		setPhone(ZERO);
		setCompanyEmail(EMPTY);
	}

	public CustomerDTO(final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password, final int phone,
			final String companyEmail) {
		super(id, dni, firstName, secondName, firstSurname, secondSurname, password, phone, companyEmail);
	}

	public static final CustomerDTO create(final UUID id, final String dni, final String firstName,
			final String secondName, final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail) {
		return new CustomerDTO(id, dni, firstName, secondName, firstSurname, secondSurname, password, phone,
				companyEmail);
	}

	public static final CustomerDTO create(final String id, final String dni, final String firstName,
			final String secondName, final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail) {
		return new CustomerDTO(getUUIDFromString(id), dni, firstName, secondName, firstSurname, secondSurname, password,
				phone, companyEmail);
	}

	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());	
	}
}
