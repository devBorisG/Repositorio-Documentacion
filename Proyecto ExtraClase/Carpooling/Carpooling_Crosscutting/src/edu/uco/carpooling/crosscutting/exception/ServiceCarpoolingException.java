package edu.uco.carpooling.crosscutting.exception;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

import edu.uco.carpooling.crosscutting.exception.enumeration.LayerException;

public class ServiceCarpoolingException extends CarpoolingCustomException {

	private static final long serialVersionUID = 76379177624916895L;

	private ServiceCarpoolingException(Exception rootException, String technicalMessage, String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.SERVICE);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage) {
		return new ServiceCarpoolingException(new Exception(), technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new ServiceCarpoolingException(rootException, technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage) {
		return new ServiceCarpoolingException(new Exception(), technicalMessage, EMPTY);
	}

	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage,
			final Exception rootException) {
		return new ServiceCarpoolingException(rootException, technicalMessage, EMPTY);
	}

	public static final CarpoolingCustomException createBussinesException(final String bussinesMessage,
			final Exception rootException) {
		return new ServiceCarpoolingException(rootException, EMPTY, bussinesMessage);
	}

	public static final CarpoolingCustomException createUserException(final String userMessage) {
		return new ServiceCarpoolingException(new Exception(), EMPTY, userMessage);
	}

	public static final CarpoolingCustomException wrapException(final String message, final CarpoolingCustomException exception) {
		if(exception.isTechnicalException()) {
			return ServiceCarpoolingException.createBussinesException(message, exception);
		}
		return exception;
	}

}
