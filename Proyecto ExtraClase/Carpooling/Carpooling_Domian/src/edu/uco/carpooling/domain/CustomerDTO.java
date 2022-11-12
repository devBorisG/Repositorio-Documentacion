package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;

public class CustomerDTO extends UserDTO {

	public CustomerDTO() {
		setId(getNewUUID());
		setDni(EMPTY);
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPhone(ZERO);
		setCompanyEmail(EMPTY);
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
	
	public static final CustomerDTO create(final UUID id) {
		return new CustomerDTO(id, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, ZERO, EMPTY);
	}
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public static final String getIntAsString(final int value) {
		return Integer.toString(value);
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
	
	public boolean exist() {
		return !UUIDHelper.isDefaultUUID(getId());
	}
	
	public boolean notExist() {
		return !exist();
	}
	
}
