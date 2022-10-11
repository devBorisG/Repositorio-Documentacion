package edu.uco.carpooling.domain;

import java.time.LocalDate;
import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;

import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.NOTHING;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;

public class CustomerDTO {

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
	
	public CustomerDTO() {
		setId(getNewUUID());
		setDni(ZERO);
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPassword(EMPTY);
		setBorn(NOTHING);
		setPhone(ZERO);
		setEmailCompany(EMPTY);
	}

	public CustomerDTO(final UUID id,final int dni,final String firstName,final String secondName,final String firstSurname,final String secondSurname,
			final String password,final LocalDate born,final int phone,final String emailCompany) {
		setId(id);
		setDni(dni);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setPassword(password);
		setBorn(born);
		setPhone(phone);
		setEmailCompany(emailCompany);
	}
	
	public static final CustomerDTO create(final UUID id,final int dni,final String firstName,final String secondName,final String firstSurname,final String secondSurname,
			final String password,final LocalDate born,final int phone,final String emailCompany) {
		return new CustomerDTO(
					id,
					dni,
					firstName,
					secondName,
					firstSurname,
					secondSurname,
					password,
					born,
					phone,
					emailCompany
				);
	}

	public UUID getId() {
		return id;
	}

	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public int getDni() {
		return dni;
	}

	public final void setDni(final int dni) {
		this.dni = isLessThan(dni, ZERO)? ZERO : dni;
	}

	public String getFirstName() {
		return firstName;
	}

	public final void setFirstName(final String firstName) {
		this.firstName = applyTrim(firstName);
	}

	public String getSecondName() {
		return secondName;
	}

	public final void setSecondName(final String secondName) {
		this.secondName = applyTrim(secondName);
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public final void setFirstSurname(final String firstSurname) {
		this.firstSurname = applyTrim(firstSurname);
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public final void setSecondSurname(final String secondSurname) {
		this.secondSurname = applyTrim(secondSurname);
	}

	public String getPassword() {
		return password;
	}

	public final void setPassword(final String password) {
		this.password = applyTrim(password);
	}

	public LocalDate getBorn() {
		return born;
	}

	public final void setBorn(final LocalDate born) {
		this.born = getDefaultDate(born);
	}

	public int getPhone() {
		return phone;
	}

	public final void setPhone(final int phone) {
		this.phone = isLessThan(phone, ZERO)? ZERO : phone;
	}

	public String getEmailCompany() {
		return emailCompany;
	}

	public final void setEmailCompany(final String emailCompany) {
		this.emailCompany = applyTrim(emailCompany);
	}
	
}
