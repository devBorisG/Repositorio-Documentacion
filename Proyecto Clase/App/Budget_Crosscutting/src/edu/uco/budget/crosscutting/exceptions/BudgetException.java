package edu.uco.budget.crosscutting.exceptions;

public class BudgetException extends RuntimeException {
	
	
	private static final long serialVersionUID = -2614577065081710653L;
	private final String userMessage;
	
	protected BudgetException(final Exception rootException,final String technicalMessage,final String userMessage) {
		super(technicalMessage,rootException);
		this.userMessage = userMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}
	
}
