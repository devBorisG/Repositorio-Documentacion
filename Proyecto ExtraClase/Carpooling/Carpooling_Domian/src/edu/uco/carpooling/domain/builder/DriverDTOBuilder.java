package edu.uco.carpooling.domain.builder;

import java.time.LocalDate;
import java.util.UUID;

import edu.uco.carpooling.domain.DriverDTO;
import static edu.uco.carpooling.domain.DriverDTO.create;

public class DriverDTOBuilder {

	private UUID id;
	private int dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private LocalDate born;
	private int phone;
	private String emailCompany;
	
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

	public final DriverDTOBuilder setDni(int dni) {
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

	public final DriverDTOBuilder setBorn(LocalDate born) {
		this.born = born;
		return this;
	}

	public final DriverDTOBuilder setPhone(int phone) {
		this.phone = phone;
		return this;
	}

	public final DriverDTOBuilder setEmailCompany(String emailCompany) {
		this.emailCompany = emailCompany;
		return this;
	}
	
	public final DriverDTO build() {
		return create(id, dni, firstName, secondName, firstSurname, secondSurname, password, born, phone, emailCompany);
	}
}
