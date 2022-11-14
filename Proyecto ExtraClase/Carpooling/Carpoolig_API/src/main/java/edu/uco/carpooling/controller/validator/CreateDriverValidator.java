package edu.uco.carpooling.controller.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
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
			messages.add(Message.createErrorMessage("Customer id is equal to default value"));
		}
	}

}
