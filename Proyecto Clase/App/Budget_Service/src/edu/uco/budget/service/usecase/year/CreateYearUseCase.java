package edu.uco.budget.service.usecase.year;

import edu.uco.budget.domain.YearDTO;

public interface CreateYearUseCase {

	void execute(YearDTO year);
}
