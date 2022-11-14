package edu.uco.budget.service.usecase.budget.implementation;

import java.util.UUID;

import edu.uco.budget.crosscutting.exception.ServiceBudgetException;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.budget.FindBudgetUseCase;
import edu.uco.budget.service.usecase.person.FindPersonById;
import edu.uco.budget.service.usecase.person.implementation.FindPersonByIdImpl;
import edu.uco.budget.service.usecase.year.FindNextYearUseCase;
import edu.uco.budget.service.usecase.year.implementation.FindNextYearUseCaseImpl;

public final class CreateBudgetUseCaseImpl implements CreateBudgetUseCase {

	private final DAOFactory factory;
	private final FindNextYearUseCase findNextYearUseCase;
	private final FindPersonById findPersonById;
	private final FindBudgetUseCase findBudgetUseCase;

	public CreateBudgetUseCaseImpl(DAOFactory factory) {
		this.factory = factory;
		findNextYearUseCase = new FindNextYearUseCaseImpl(factory);
		findPersonById = new FindPersonByIdImpl(factory);
		findBudgetUseCase = new FindBudgetUseCaseImpl(factory);
	}

	@Override
	public final void execute(final BudgetDTO budget) {

		// 1. Que el año de presupuesto exista
		final YearDTO year = findNextYearUseCase.execute();
		// 2. Que la persona exista
		final PersonDTO person = finPerson(budget.getPerson().getId());
		// 3. Que no existe un presupuesto para el mismo usuario el mismo año
		budget.setYear(year);
		budget.setPerson(person);
		validateIfBudgetExist(budget);
		
		budget.setId(UUIDHelper.getNewUUID());
		factory.getBudgetDAO().create(budget);
	}

	private final PersonDTO finPerson(UUID id) {
		final PersonDTO person = findPersonById.execute(id);

		if (person.notExist()) {
			throw ServiceBudgetException
					.createUserException(Messages.CreateBudgetUseCaseImpl.BUSSINES_PERSON_DOES_NOT_EXIST);
		}

		return person;
	}

	private final void validateIfBudgetExist(final BudgetDTO budget) {

		if (!findBudgetUseCase.execute(budget).isEmpty()) {
			throw ServiceBudgetException.createUserException(Messages.CreateBudgetUseCaseImpl.BUSSINES_BUDGET_EXIST);
		}
	}
}