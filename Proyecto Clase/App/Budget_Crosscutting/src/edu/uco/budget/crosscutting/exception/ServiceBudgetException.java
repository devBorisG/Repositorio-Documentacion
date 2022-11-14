package edu.uco.budget.crosscutting.exception;

import static edu.uco.budget.crosscutting.helper.StringHelper.EMPTY;

import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class ServiceBudgetException extends BudgetCustomException{

	private static final long serialVersionUID = -2829539408066336692L;

	private ServiceBudgetException(final Exception rootException, final String technicalMessage,
			final String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.SERVICE);
	}

	public static final BudgetCustomException create(final String userMessage, final String technicalMessage) {
		return new ServiceBudgetException(new Exception(), technicalMessage, userMessage);
	}

	public static final BudgetCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new ServiceBudgetException(rootException, technicalMessage, userMessage);
	}

	public static final BudgetCustomException createTechnicalException(final String technicalMessage) {
		return new ServiceBudgetException(new Exception(), technicalMessage, EMPTY);
	}

	public static final BudgetCustomException createTechnicalException(final String technicalMessage,
			final Exception rootException) {
		return new ServiceBudgetException(rootException, technicalMessage, EMPTY);
	}

	public static final BudgetCustomException createUserException(final String userMessage) {
		return new ServiceBudgetException(new Exception(), userMessage, EMPTY);
	}
	
	
}
