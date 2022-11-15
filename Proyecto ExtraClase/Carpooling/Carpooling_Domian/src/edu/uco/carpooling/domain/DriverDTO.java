package edu.uco.carpooling.domain;

import java.util.UUID;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.domain.builder.AuthorizedCategoryDTOBuilder.getAuthorizedCategoryDTOBuilder;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDFromString;

public class DriverDTO extends UserDTO{
		
	private String licenseNumber;
	private AuthorizedCategoryDTO authorizedCategory;

	public DriverDTO() {
		setId(getNewUUID());
		setLicenseNumber(EMPTY);
		setAuthorizedCategory(getAuthorizedCategoryDTOBuilder().build());
	}

	public DriverDTO(final UUID id,final String licenseNumber, final AuthorizedCategoryDTO authorizedCategory) {
		setId(getDefaultUUID(id));
		setLicenseNumber(licenseNumber);
		setAuthorizedCategory(authorizedCategory);
	}
	
	
	public static final DriverDTO create (final UUID id,final String licenseNumber,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(id,licenseNumber,authorizedCategory);
	}
	
	public static final DriverDTO create (final String id,final String licenseNumber,final AuthorizedCategoryDTO authorizedCategory) {
		return new DriverDTO(getUUIDFromString(id),licenseNumber,authorizedCategory);
	}
	
	public static final DriverDTO create (final UUID id) {
		return new DriverDTO(id, EMPTY, getAuthorizedCategoryDTOBuilder().build());
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
	
	public boolean exist() {
		return !UUIDHelper.isDefaultUUID(getId());
	}

	public boolean notExist() {
		return !exist();
	}
	
}
