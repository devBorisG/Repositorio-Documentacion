package edu.uco.budget.service.usecase.budget;

import java.util.List;

import edu.uco.budget.domain.BudgetDTO;

public interface FindBudgetUseCase {

	List<BudgetDTO> execute(BudgetDTO budget);
}
