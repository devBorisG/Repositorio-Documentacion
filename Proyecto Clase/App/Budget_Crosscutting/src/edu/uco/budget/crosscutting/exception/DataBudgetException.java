package edu.uco.budget.crosscutting.exception;

import static edu.uco.budget.crosscutting.helper.StringHelper.EMPTY;

import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class DataBudgetException extends BudgetCustomException {

	private static final long serialVersionUID = -1512925963471556667L;

	private DataBudgetException(final Exception rootException, final String technicalMessage,
			final String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.DATA);
	}

	public static final BudgetCustomException create(final String userMessage, final String technicalMessage) {
		return new DataBudgetException(new Exception(), technicalMessage, userMessage);
	}

	public static final BudgetCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new DataBudgetException(rootException, technicalMessage, userMessage);
	}

	public static final BudgetCustomException createTechnicalException(final String technicalMessage) {
		return new DataBudgetException(new Exception(), technicalMessage, EMPTY);
	}

	public static final BudgetCustomException createTechnicalException(final String technicalMessage,
			final Exception rootException) {
		return new DataBudgetException(rootException, technicalMessage, EMPTY);
	}

	public static final BudgetCustomException createUserException(final String userMessage,
			final Exception rootException) {
		return new DataBudgetException(rootException, userMessage, userMessage);
	}
}
