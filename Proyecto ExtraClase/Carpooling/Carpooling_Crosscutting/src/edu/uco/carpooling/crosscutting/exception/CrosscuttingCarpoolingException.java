package edu.uco.carpooling.crosscutting.exception;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import edu.uco.carpooling.crosscutting.exception.enumeration.LayerException;

public class CrosscuttingCarpoolingException extends CarpoolingCustomException{

	private static final long serialVersionUID = -748918257971513736L;

	private CrosscuttingCarpoolingException(final Exception rootException, final String technicalMessage,
			final String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.CROSSCUTTING);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage) {
		return new CrosscuttingCarpoolingException(new Exception(), technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new CrosscuttingCarpoolingException(rootException, technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage) {
		return new CrosscuttingCarpoolingException(new Exception(), technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage, final Exception rootException) {
		return new CrosscuttingCarpoolingException(rootException, technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createUserException(final String userMessage, final Exception rootException) {
		return new CrosscuttingCarpoolingException(rootException, userMessage, userMessage);
	}
}
