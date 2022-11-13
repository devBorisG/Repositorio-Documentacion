package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.domain.builder.AuthorizedCategoryDTOBuilder.getAuthorizedCategoryDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class DriverDTO extends UserDTO{
		
	private String licenseNumber;
	private AuthorizedCategoryDTO authorizedCategory;

	public DriverDTO() {
		setId(getNewUUID());
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPassword(EMPTY);
		setPhone(ZERO);
		setCompanyEmail(EMPTY);
		setLicenseNumber(EMPTY);
		setAuthorizedCategory(getAuthorizedCategoryDTOBuilder().build());
	}
	
	public DriverDTO(final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber, final AuthorizedCategoryDTO authorizedCategory) {
		setId(getDefaultUUID(id));
		setDni(dni);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setPassword(password);
		setPhone(phone);
		setCompanyEmail(companyEmail);
		setLicenseNumber(licenseNumber);
		setAuthorizedCategory(authorizedCategory);
	}
	
	
	public static final DriverDTO create (final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(id,dni,firstName,secondName,firstSurname,secondSurname,password,phone,companyEmail,
				licenseNumber,authorizedCategory);
	}
	
	public static final DriverDTO create (final String id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(getUUIDFromString(id),dni,firstName,secondName,firstSurname,secondSurname,password,phone,companyEmail,
				licenseNumber,authorizedCategory);
	}
	
	public static final DriverDTO create (final UUID id) {
		return new DriverDTO(id, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, ZERO, EMPTY, EMPTY, null);
	}
	
	public static final String getUUIDAsString(final UUID value) {
		return getDefaultUUID(value).toString();
	}
	
	public static final String getIntAsString(final int value) {
		return Integer.toString(value);
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public AuthorizedCategoryDTO getAuthorizedCategory() {
		return authorizedCategory;
	}

	public void setAuthorizedCategory(AuthorizedCategoryDTO authorizedCategory) {
		this.authorizedCategory = authorizedCategory;
	}
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
	
}
