package edu.uco.carpooling.domain;

import java.util.UUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper. getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.domain.builder.AuthorizedCategoryDTOBuilder.getAuthorizedCategoryDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class DriverDTO extends UserDTO{
		
	private String licenseNumber;
	private String expedition;
	private AuthorizedCategoryDTO authorizedCategory;

	public DriverDTO(final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber,
			final String expedition,final AuthorizedCategoryDTO authorizedCategory) {
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
		setExpedition(expedition);
		setAuthorizedCategory(authorizedCategory);
	}
	
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
		setExpedition(EMPTY);
		setAuthorizedCategory(getAuthorizedCategoryDTOBuilder().build());
	}
	
	public static final DriverDTO create (final UUID id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber,
			final String expedition,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(id,dni,firstName,secondName,firstSurname,secondSurname,password,phone,companyEmail,
				licenseNumber,expedition,authorizedCategory);
	}
	
	public static final DriverDTO create (final String id, final String dni, final String firstName, final String secondName,
			final String firstSurname, final String secondSurname, final String password,
			final int phone, final String companyEmail,final String licenseNumber,
			final String expedition,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(getUUIDFromString(id),dni,firstName,secondName,firstSurname,secondSurname,password,phone,companyEmail,
				licenseNumber,expedition,authorizedCategory);
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getExpedition() {
		return expedition;
	}

	public void setExpedition(String expedition) {
		this.expedition = expedition;
	}

	public AuthorizedCategoryDTO getAuthorizedCategory() {
		return authorizedCategory;
	}

	public void setAuthorizedCategory(AuthorizedCategoryDTO authorizedCategory) {
		this.authorizedCategory = authorizedCategory;
	}
	
}
