package edu.uco.carpooling.controller.validator.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.crosscutting.messages.Messages;
import static edu.uco.carpooling.crosscutting.helper.StringHelper.isEmpty;

public class CreateCustomerValidator implements Validator<CustomerDTO> {
	
	@Override
	public List<Message> validate(CustomerDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerEmail(dto.getCompanyEmail(), messages);
		validatePassword(dto.getPassword(), messages);
		validateFisrtname(dto.getFirstName(), messages);
		validatFisrtsurname(dto.getFirstSurname(), messages);
		validateSecondname(dto.getSecondSurname(), messages);
		
		return messages;
	}
	
	private void validateCustomerEmail(String customerEmail, List<Message> message) {
		if(validateEmail(customerEmail)) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_EMAIL_IS_INCORRECT));
		}
	}
	
	private boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		return mather.find();
	}
	
	private void validatePassword(String password, List<Message> message) {
		
		int length = password.length();
		if (length < 6) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_PASSWORD_IS_INCORRECT));
		}
	}
	
	private void validateFisrtname(String firstName, List<Message> message) {
		if(isEmpty(firstName)) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_FIRST_NAME_IS_EMPTY));
		}
	}
	private void validatFisrtsurname(String firstName, List<Message> message) {
		if(isEmpty(firstName)) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_FIRST_SURNNAME_IS_EMPTY));
		}
	}
	private void validateSecondname(String firstName, List<Message> message) {
		if(isEmpty(firstName)) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_SECOND_SURNNAME_IS_EMPTY));
		}
	}
}
