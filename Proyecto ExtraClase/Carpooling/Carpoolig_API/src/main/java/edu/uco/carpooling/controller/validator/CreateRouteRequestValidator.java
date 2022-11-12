package edu.uco.carpooling.controller.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.RouteRequestDTO;

public class CreateRouteRequestValidator implements Validator<RouteRequestDTO>{

	@Override
	public List<Message> validate(RouteRequestDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerId(dto.getCustomer().getId(), messages);
		
		return messages;
	}

	private void validateCustomerId(UUID customerId, List<Message> messages) {
		
		if(UUIDHelper.isDefaultUUID(customerId)) {
			messages.add(Message.createErrorMessage("Customer id is equal to default value"));
		}
	}
}
