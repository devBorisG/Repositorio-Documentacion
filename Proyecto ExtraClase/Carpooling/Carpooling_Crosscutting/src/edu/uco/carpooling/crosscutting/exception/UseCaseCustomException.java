package edu.uco.carpooling.crosscutting.exception;

import edu.uco.carpooling.crosscutting.exception.enumeration.LayerException;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

public class UseCaseCustomException extends CarpoolingCustomException{

	protected UseCaseCustomException(Exception rootException, String technicalMessage, String userMessage,
			LayerException layer) {
		super(rootException, technicalMessage, userMessage, layer);
	}

	private static final long serialVersionUID = 1L;
	
	private UseCaseCustomException(final Exception rootException, final String technicalMessage,
			final String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.USECASE);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage) {
		return new UseCaseCustomException(new Exception(), technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new UseCaseCustomException(rootException, technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage) {
		return new UseCaseCustomException(new Exception(), technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage, final Exception rootException) {
		return new UseCaseCustomException(rootException, technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createUserException(final String userMessage, final Exception rootException) {
		return new UseCaseCustomException(rootException, userMessage, userMessage);
	}
	
	public static final CarpoolingCustomException createUserException(final String userMessage) {
		return new UseCaseCustomException(new Exception(), userMessage, EMPTY);
	}

}
