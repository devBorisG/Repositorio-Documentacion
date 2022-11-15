package edu.uco.carpooling.controller.validator.vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.helper.StringHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.domain.VehicleDTO;

public class CreateVehicleValidator implements Validator<VehicleDTO> {

	@Override
	public List<Message> validate(VehicleDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateOwnerId(dto.getOwner().getId(), messages);
		validatePlate(dto.getPlate(), messages);
		validateNumberEnrollment(dto.getNumberEnrollment(), messages);
		
		return messages;
	}

	private void validateOwnerId(UUID ownerId, List<Message> messages) {
		if (UUIDHelper.isDefaultUUID(ownerId)) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_OWNER_ID_IS_DEFAULT));
		}
	}

	private void validatePlate(String plate, List<Message> messages) {
		if (StringHelper.isEmpty(plate)) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_PLATE_IS_EMPTY));
		}
		
		if (plate.length() != 6) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_LENGTH_PLATE_IS_NOT_CORRECT));
		}
		
		if (!haveCorrectFormat(plate)) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_DONT_HAVE_CORRECT_FORMAT));
		}
	}
	
	private void validateNumberEnrollment(String numEnrollment, List<Message> messages) {
		if (StringHelper.isEmpty(numEnrollment)) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_NUMBER_ENROLLMENT_IS_EMPTY));
		}
		
		if (numEnrollment.length() != 16) {
			messages.add(Message.createErrorMessage(Messages.CreateVehicleValidator.ERROR_NUMBER_ENROLLMENT_INCORRECT_LENGTH));
		}
	}
	

	private boolean haveCorrectFormat(String plate) {
		return (areLetters(plate.substring(0, 3)) && areNumbers(plate.substring(3, 6))); 
	}

	private boolean areLetters(String string) {
		return string.matches("^[a-zA-Z]*$");
	}

	private boolean areNumbers(String numbers) {
		return numbers.matches("[+-]?\\d*(\\.\\d+)?");
	}
}
