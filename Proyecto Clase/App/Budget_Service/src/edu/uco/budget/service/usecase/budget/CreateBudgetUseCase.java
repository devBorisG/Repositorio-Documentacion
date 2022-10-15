package edu.uco.budget.service.usecase.budget;

import edu.uco.budget.domain.BudgetDTO;

public interface CreateBudgetUseCase {
	
	void execute(BudgetDTO budget);
}
