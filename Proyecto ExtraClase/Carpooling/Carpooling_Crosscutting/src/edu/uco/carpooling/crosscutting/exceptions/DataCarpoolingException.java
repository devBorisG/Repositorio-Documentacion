package edu.uco.carpooling.crosscutting.exceptions;

public class DataCarpoolingException extends RuntimeException{

	private static final long serialVersionUID = -6716192062381901469L;
	private final String userMessage;	
	
	protected DataCarpoolingException(final Exception rootException,final String technicalMessage,final String message) {
		super(technicalMessage,rootException);
		this.userMessage = message;
	}

	public String getUserMessage() {
		return userMessage;
	}
}
