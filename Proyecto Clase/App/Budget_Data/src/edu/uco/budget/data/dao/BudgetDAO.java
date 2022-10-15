package edu.uco.budget.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.budget.domain.BudgetDTO;

public interface BudgetDAO {

	void create(final BudgetDTO budget);
	
	List<BudgetDTO> find(final BudgetDTO budget);
	
	void update(final BudgetDTO budget);
	
	void delete(final UUID id);
}
