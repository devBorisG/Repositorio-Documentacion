package edu.uco.budget.service.command.implementation;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.command.CreateYearCommand;
import edu.uco.budget.service.usecase.year.CreateYearUseCase;
import edu.uco.budget.service.usecase.year.implementation.CreateYearUseCaseImpl;

public class CreateYearCommandImpl implements CreateYearCommand{

	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.SQLSERVER);
	private final CreateYearUseCase useCase = new CreateYearUseCaseImpl(factory);
	
	@Override
	public void execute(YearDTO year) {
		try {
			factory.initTransaction();
			useCase.execute(year);
			factory.confirmTransaction();
		} catch (BudgetCustomException exception) {
			factory.cancelTransaction();
			throw exception;
		} catch (Exception exception) {
			factory.cancelTransaction();
		} finally {
			factory.closeConnection();
		}
	}
}
