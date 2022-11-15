package edu.uco.carpooling.domain;

import java.util.UUID;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.carpooling.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getNewUUID;

public class UserDTO {

	private UUID id;
	private String dni;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	private String password;
	private int phone;
	private String companyEmail;
	
	public UserDTO() {
		setId(getNewUUID());
		setDni(EMPTY);
		setFirstName(EMPTY);
		setSecondName(EMPTY);
		setFirstSurname(EMPTY);
		setSecondSurname(EMPTY);
		setPassword(EMPTY);
		setPhone(ZERO);
		setCompanyEmail(EMPTY);
	}

	public UserDTO(final UUID id,final String dni,final String firstName,final String secondName,final String firstSurname,final String secondSurname,
			final String password,final int phone,final String emailCompany) {
		setId(id);
		setDni(dni);
		setFirstName(firstName);
		setSecondName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setPassword(password);
		setPhone(phone);
		setCompanyEmail(emailCompany);
	}
	
	public UUID getId() {
		return id;
	}

	public final void setId(final UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getDni() {
		return dni;
	}

	public final void setDni(final String dni) {
		this.dni = applyTrim(dni);
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
}
