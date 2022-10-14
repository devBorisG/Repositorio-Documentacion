package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.util.UUID;

import edu.uco.carpooling.domain.UserDTO;
import static edu.uco.carpooling.domain.UserDTO.create;

public class UserDTOBuilder {
	
	private UUID id;
	private int dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private LocalDate born;
	private int phone;
	private String companyEmail;
	private String referencePoint;
	
	private UserDTOBuilder() {
		super();
	}
	
	public static final UserDTOBuilder getUserDTOBuilder() {
		return new UserDTOBuilder();
	}

	public final UserDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}

	public final UserDTOBuilder setDni(int dni) {
		this.dni = dni;
		return this;
	}

	public final UserDTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public final UserDTOBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public final UserDTOBuilder setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
		return this;
	}

	public final UserDTOBuilder setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
		return this;
	}

	public final UserDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public final UserDTOBuilder setBorn(LocalDate born) {
		this.born = born;
		return this;
	}

	public final UserDTOBuilder setPhone(int phone) {
		this.phone = phone;
		return this;
	}

	public final UserDTOBuilder setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
		return this;
	}

	public final UserDTOBuilder setReferencePoint(String referencePoint) {
		this.referencePoint = referencePoint;
		return this;
	}
	
	public final UserDTO build() {
		return create(id, dni, firstName, secondName, firstSurname, secondSurname, password, born, phone, companyEmail, referencePoint);
	}
}
