package edu.uco.carpooling.service.business.vehicle.implementation;

import edu.uco.carpooling.crosscutting.exception.ServiceCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.service.business.vehicle.FormatPlate;

public class FormatPlateImpl implements FormatPlate {

	@Override
	public void execute(String plate) {
		lengthPlate(plate);
		haveCorrectFormat(plate);
	}

	private void lengthPlate(String plate) {
		if (plate.length() != 6) {
			throw ServiceCarpoolingException
					.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_LENGTH_PLATE_IS_NOT_CORRECT);
		}
	}

	private void haveCorrectFormat(String plate) {
		areLetters(plate.substring(0, 3));
		areNumbers(plate.substring(3, 6));
	}

	private void areLetters(String string) {
		if (!string.matches("^[a-zA-Z]*$")) {
			throw ServiceCarpoolingException
					.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_ARE_NOT_LETTERS);
		}
	}

	private void areNumbers(String numbers) {
		if (!numbers.matches("[+-]?\\d*(\\.\\d+)?")) {
			throw ServiceCarpoolingException
					.createUserException(Messages.CreateVehicleUseCaseImpl.BUSSINES_ARE_NOT_NUMBERS);
		}
	}
}
