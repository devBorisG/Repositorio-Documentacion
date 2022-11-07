package edu.uco.carpooling.domain.builder;

import java.util.UUID;

import edu.uco.carpooling.domain.CustomerDTO;
import static edu.uco.carpooling.domain.CustomerDTO.create;

public class CustomerDTOBuilder {
	
	private UUID id;
	private int dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private int phone;
	private String companyEmail;
	private String referencePoint;
	
	private CustomerDTOBuilder() {
		super();
	}
	
	public static final CustomerDTOBuilder getUserDTOBuilder() {
		return new CustomerDTOBuilder();
	}

	public final CustomerDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}

	public final CustomerDTOBuilder setDni(int dni) {
		this.dni = dni;
		return this;
	}

	public final CustomerDTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public final CustomerDTOBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public final CustomerDTOBuilder setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
		return this;
	}

	public final CustomerDTOBuilder setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
		return this;
	}

	public final CustomerDTOBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public final CustomerDTOBuilder setPhone(int phone) {
		this.phone = phone;
		return this;
	}

	public final CustomerDTOBuilder setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
		return this;
	}

	public final CustomerDTOBuilder setReferencePoint(String referencePoint) {
		this.referencePoint = referencePoint;
		return this;
	}
	
	public final CustomerDTO build() {
		return create(id, dni, firstName, secondName, firstSurname, secondSurname, password, phone, companyEmail, referencePoint);
	}
}
