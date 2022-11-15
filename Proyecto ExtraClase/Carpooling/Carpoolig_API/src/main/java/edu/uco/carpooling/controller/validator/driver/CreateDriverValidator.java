package edu.uco.carpooling.controller.validator.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.domain.DriverDTO;

public class CreateDriverValidator implements Validator<DriverDTO>{

	@Override
	public List<Message> validate(DriverDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerId(dto.getId(), messages);
		
		return messages;
	}

	private void validateCustomerId(UUID driverId, List<Message> messages) {
		
		if(UUIDHelper.isDefaultUUID(driverId)) {
			messages.add(Message.createErrorMessage(Messages.CreateDriverValidator.ERROR_DRIVER_ID_IS_DEFAULT));
		}
	}

}
