package edu.uco.carpooling.crosscutting.exception;

import edu.uco.carpooling.crosscutting.exception.enumeration.LayerException;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.applyTrim;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.isEmpty;
import static edu.uco.carpooling.crosscutting.helper.ObjectHelper.getDefaultIfNull;

class CarpoolingCustomException extends RuntimeException{

	private static final long serialVersionUID = -1248346293196099172L;
	private String userMessage;
	private LayerException layer;
	
	protected CarpoolingCustomException(final Exception rootException, final String technicalMessage, final String userMessage, final LayerException layer) {
		super(applyTrim(technicalMessage),getDefaultIfNull(rootException, new Exception()));
		setUserMessage(userMessage);
		setLayer(layer);
	}

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
		this.layer = getDefaultIfNull(layer, LayerException.APLICATION);
	}
	
	public final boolean isTechnicalException() {
		return isEmpty(getUserMessage());
	}
	
	
}
