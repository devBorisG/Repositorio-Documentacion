package edu.uco.budget.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.budget.domain.PersonDTO;

public interface PersonDAO {

	void create(final PersonDTO person);
	
	List<PersonDTO> find(final PersonDTO person);
	
	void update(final PersonDTO person);
	
	void delete(final UUID id);
}
