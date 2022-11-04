package edu.uco.carpooling.domain;

import java.sql.Date;
import java.util.UUID;


import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;

import static edu.uco.carpooling.crosscutting.helper.DateHelper.getDefaultDate;
import static edu.uco.carpooling.crosscutting.helper.DateHelper.NOTHING;

import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class UserDTO {

	private UUID id;
	private int dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private Date born;
	private int phone;
	private String companyEmail;
	
	public UserDTO() {
		setId(getNewUUID());
		setDni(ZERO);
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPassword(EMPTY);
		setBorn(NOTHING);
		setPhone(ZERO);
		setCompanyEmail(EMPTY);
	}

	public UserDTO(final UUID id,final int dni,final String firstName,final String secondName,final String firstSurname,final String secondSurname,
			final String password,final Date born,final int phone,final String emailCompany) {
		setId(id);
		setDni(dni);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setPassword(password);
		setBorn(born);
		setPhone(phone);
		setCompanyEmail(emailCompany);
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

	public Date getBorn() {
		return born;
	}

	public final void setBorn(final Date born) {
		this.born = getDefaultDate(born);
	}

	public int getPhone() {
		return phone;
	}

	public final void setPhone(final int phone) {
		this.phone = isLessThan(phone, ZERO)? ZERO : phone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public final void setCompanyEmail(final String companyEmail) {
		this.companyEmail = applyTrim(companyEmail);
	}
	
	public final String getIdAsString() {
		return getUUIDAsString(getId());
	}
	
}
