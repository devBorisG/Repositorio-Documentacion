package edu.uco.budget.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.budget.domain.YearDTO;

public interface YearDAO {
	
	void create(final YearDTO year);
	
	List<YearDTO> find(final YearDTO year);
	
	void update(final YearDTO year);
	
	void delete(final UUID id);
}
