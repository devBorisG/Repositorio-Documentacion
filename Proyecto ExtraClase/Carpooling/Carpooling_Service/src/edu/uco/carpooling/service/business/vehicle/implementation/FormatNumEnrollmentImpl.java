package edu.uco.carpooling.service.business.vehicle.implementation;

import edu.uco.carpooling.crosscutting.exception.ServiceCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.service.business.vehicle.FormatNumEnrollment;

public class FormatNumEnrollmentImpl implements FormatNumEnrollment {

	@Override
	public void execute(String numEnrollment) {
		if(numEnrollment.length() != 16) {
			throw ServiceCarpoolingException.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_NUMBER_ENROLLMENT_INCORRECT_FORMAT);
		}
	}

}
