package edu.uco.budget.service.usecase.year.implementation;

import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.year.CreateYearUseCase;

public class CreateYearUseCaseImpl implements CreateYearUseCase{
	
	private final DAOFactory factory;
	
	public CreateYearUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
	}

	@Override
	public void execute(YearDTO year) {
		factory.getYearDAO().create(year);
	}

}
