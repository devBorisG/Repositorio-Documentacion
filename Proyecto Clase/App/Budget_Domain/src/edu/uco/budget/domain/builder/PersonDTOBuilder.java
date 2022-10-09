package edu.uco.budget.domain.builder;

import java.util.UUID;

import edu.uco.budget.domain.PersonDTO;
import static edu.uco.budget.domain.PersonDTO.create;

public class PersonDTOBuilder {
	
	private UUID id;
	private String idCard;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secondSurname;
	
	private PersonDTOBuilder() {
		super();
	}
	
	public static final PersonDTOBuilder getPersonDTOBuilder() {
		return new PersonDTOBuilder();
	}
	
	public final PersonDTOBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public final PersonDTOBuilder setIdCard(String idCard) {
		this.idCard = idCard;
		return this;
	}
	
	public final PersonDTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public final PersonDTOBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}
	
	public final PersonDTOBuilder setFirstSurname(String firstSurname) {
		this.firstName = firstSurname;
		return this;
	}
	
	public final PersonDTOBuilder setSecondSurname(String secondSurname) {
		this.firstName = secondSurname;
		return this;
	}
	
	public final PersonDTO build() {
		return create(id, idCard, firstName, secondName, firstSurname, secondSurname);
	}
}
