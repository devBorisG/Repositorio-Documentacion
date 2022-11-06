package edu.uco.budget.crosscutting.exception;

import static edu.uco.budget.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.budget.crosscutting.helper.StringHelper.isEmpty;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull;


//*THIS IS THE FATHER TO THE OTHERS EXCEPTIONS*

//default?
class BudgetCustomException extends RuntimeException{
	
	
	private static final long serialVersionUID = -2614577065081710653L;
	private String userMessage;
	private LayerException layer;
	
	protected BudgetCustomException(final Exception rootException,final String technicalMessage,final String userMessage, final LayerException layer) {
		super(applyTrim(technicalMessage),getDefaultIfNull(rootException, new Exception()));
		setUserMessage(userMessage);
		setLayer(layer);
	}

	//final?
	public final String getUserMessage() {
		return userMessage;
	}

	private final void setUserMessage(final String userMessage) {
		this.userMessage = applyTrim(userMessage);
	}

	public final LayerException getLayer() {
		return layer;
	}

	private final void setLayer(final LayerException layer) {
		this.layer = getDefaultIfNull(layer, LayerException.APPLICATION);
	}
	
	public final boolean isTechnicalException() {
		return isEmpty(getUserMessage());
	}

}
