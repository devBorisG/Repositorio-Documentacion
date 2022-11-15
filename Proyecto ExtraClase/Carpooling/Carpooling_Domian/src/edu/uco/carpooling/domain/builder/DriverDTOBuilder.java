package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
import static edu.uco.carpooling.domain.DriverDTO.create;

public class DriverDTOBuilder {

	private UUID id;
	private String licenseNumber;
	private AuthorizedCategoryDTO authorizedCategory;
	
	private DriverDTOBuilder() {
		super();
	}
	
	public static final DriverDTOBuilder getDriverDTOBuilder() {
		return new DriverDTOBuilder();
	}

	public final DriverDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	public DriverDTOBuilder setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
		return this;
	}
	
	public DriverDTOBuilder setAuthorizedCategory(AuthorizedCategoryDTO authorizedCategory) {
		this.authorizedCategory = authorizedCategory;
		return this;
	}
	
	public final DriverDTO build() {
		return create(id,licenseNumber,authorizedCategory);
	}
}
