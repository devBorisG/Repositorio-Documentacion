package edu.uco.carpooling.controller.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.CustomerDTO;

public class CreateCustomerValidator implements Validator<CustomerDTO> {

	@Override
	public List<Message> validate(CustomerDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerId(dto.getId(), messages);
		
		return messages;
	}

	private void validateCustomerId(UUID customerId, List<Message> messages) {
		
		if(UUIDHelper.isDefaultUUID(customerId)) {
			messages.add(Message.createErrorMessage("Customer id is equal to default value"));
		}
	}
}
