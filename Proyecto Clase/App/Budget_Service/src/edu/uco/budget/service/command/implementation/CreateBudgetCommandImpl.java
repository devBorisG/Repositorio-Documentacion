package edu.uco.budget.service.command.implementation;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.service.command.CreateBudgetCommand;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.budget.implementation.CreateBudgetUseCaseImpl;

public class CreateBudgetCommandImpl implements CreateBudgetCommand{

	private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.SQLSERVER);
	private final CreateBudgetUseCase useCase = new CreateBudgetUseCaseImpl(factory);
	
	@Override
	public final void execute(final BudgetDTO budget) {
		try {
			factory.initTransaction();
			useCase.execute(budget);
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
