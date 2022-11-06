package edu.uco.carpooling.crosscutting.exception;

import edu.uco.carpooling.crosscutting.exception.enumeration.LayerException;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.EMPTY;

public class DataCarpoolingException extends CarpoolingCustomException {

	private static final long serialVersionUID = -6716192062381901469L;

	private DataCarpoolingException(final Exception rootException, final String technicalMessage,
			final String userMessage) {
		super(rootException, technicalMessage, userMessage, LayerException.DATA);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage) {
		return new DataCarpoolingException(new Exception(), technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException create(final String userMessage, final String technicalMessage,
			final Exception rootException) {
		return new DataCarpoolingException(rootException, technicalMessage, userMessage);
	}

	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage) {
		return new DataCarpoolingException(new Exception(), technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createTechnicalException(final String technicalMessage, final Exception rootException) {
		return new DataCarpoolingException(rootException, technicalMessage,EMPTY);
	}
	
	public static final CarpoolingCustomException createUserException(final String userMessage, final Exception rootException) {
		return new DataCarpoolingException(rootException, userMessage, userMessage);
	}
}
