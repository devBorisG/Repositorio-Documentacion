package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
import static edu.uco.carpooling.domain.DriverDTO.create;

public class DriverDTOBuilder {

	private UUID id;
	private String dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private int phone;
	private String companyEmail;
	private String licenseNumber;
	private String expedition;
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

	public final DriverDTOBuilder setDni(String dni) {
		this.dni = dni;
		return this;
	}

	public final DriverDTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public final DriverDTOBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public final DriverDTOBuilder setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
		return this;
	}

	public final DriverDTOBuilder setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
		return this;
	}

	public final DriverDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public final DriverDTOBuilder setPhone(int phone) {
		this.phone = phone;
		return this;
	}

	public final DriverDTOBuilder setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
		return this;
	}
	
	public DriverDTOBuilder setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
		return this;
	}

	public DriverDTOBuilder setExpedition(String expedition) {
		this.expedition = expedition;
		return this;
	}

	public DriverDTOBuilder setAuthorizedCategory(AuthorizedCategoryDTO authorizedCategory) {
		this.authorizedCategory = authorizedCategory;
		return this;
	}
	
	public final DriverDTO build() {
		return create(id, dni, firstName, secondName, firstSurname, secondSurname, password, phone, companyEmail,licenseNumber,expedition,authorizedCategory);
	}
}
