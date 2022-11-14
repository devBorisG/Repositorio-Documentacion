package edu.uco.carpooling.controller.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.CustomerDTO;

public class CreateCustomerValidator implements Validator<CustomerDTO> {

	@Override
	public List<Message> validate(CustomerDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerId(dto.getId(), messages);
		validateCustomerEmail(dto.getCompanyEmail(), messages);
		validatePassword(dto.getPassword(), messages);
		
		return messages;
	}

	private void validateCustomerId(UUID customerId, List<Message> messages) {
		
		if(UUIDHelper.isDefaultUUID(customerId)) {
			messages.add(Message.createErrorMessage("Customer id is equal to default value"));
		}
	}
	
	private void validateCustomerEmail(String customerEmail, List<Message> message) {
		if(!ValidateEmail(customerEmail)) {
			message.add(Message.createErrorMessage("Email is not valied"));
		}
	}
	
	private boolean ValidateEmail(String email) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9-\\+]+(\\{A-Za-z0-9-]+)*@[uco]+(\\.[net]+)*(\\.[co])$");
		Matcher mather = pattern.matcher(email);
		return mather.find();
	}
	
	private void validatePassword(String password, List<Message> message) {
		
		int length = password.length();
		if (length < 6) {
			message.add(Message.createErrorMessage("Password is not valied"));
		}
	}	
}
