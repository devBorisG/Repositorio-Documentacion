package edu.uco.budget.service.command;

import edu.uco.budget.domain.YearDTO;

public interface CreateYearCommand {

	void execute(YearDTO year);
}
