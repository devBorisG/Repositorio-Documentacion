	package edu.uco.budget.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.budget.domain.BudgetDTO;

public interface BudgetDAO {
	
	//No sirve de nada poner los parametros de tipo final ya que
	//al ser un contrato no agrega ni quita valor
	
	void create( BudgetDTO budget);
	
	List<BudgetDTO> find( BudgetDTO budget);
	
	void update( BudgetDTO budget);
	
	void delete( UUID id);
}
