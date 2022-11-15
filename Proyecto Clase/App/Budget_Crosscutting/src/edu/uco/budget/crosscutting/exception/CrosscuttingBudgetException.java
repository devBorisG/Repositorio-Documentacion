package edu.uco.budget.crosscutting.exception;

import static edu.uco.budget.crosscutting.helper.StringHelper.EMPTY;

import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class CrosscuttingBudgetException extends BudgetCustomException{

	private static final long serialVersionUID = 5178420088587301513L;

	
	protected CrosscuttingBudgetException(Exception rootException, String technicalMessage, String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.CROSSCUTING);
	}

	public static final BudgetCustomException create(final String userMessage, final String technicalMessage) {
		return new CrosscuttingBudgetException(new Exception(), technicalMessage, userMessage);
	}
	
	public static final BudgetCustomException create(final String userMessage, final String technicalMessage, final Exception rootException) {
		return new CrosscuttingBudgetException(rootException, technicalMessage, userMessage);
	}
	
	public static final BudgetCustomException createTechnicalException(final String technicalMessage) {
		return new CrosscuttingBudgetException(new Exception(), technicalMessage, EMPTY);
	}
	
	public static final BudgetCustomException createTechnicalException(final String technicalMessage, final Exception rootException) {
		return new CrosscuttingBudgetException(rootException, technicalMessage, EMPTY);
	}

	public static final BudgetCustomException createUserException(final String userMessage, final Exception rootException) {
		return new CrosscuttingBudgetException(rootException, userMessage, userMessage);
	}
}
