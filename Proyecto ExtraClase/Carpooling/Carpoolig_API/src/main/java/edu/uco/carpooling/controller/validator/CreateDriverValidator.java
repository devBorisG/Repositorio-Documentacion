package edu.uco.carpooling.controller.validator;

import static edu.uco.carpooling.crosscutting.helper.StringHelper.isEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.crosscutting.messages.Messages;

public class CreateDriverValidator implements Validator<DriverDTO>{

	@Override
	public List<Message> validate(DriverDTO dto) {
		List<Message> messages = new ArrayList<>();
		
		validateCustomerId(dto.getId(), messages);
		validateLicenseNumber(dto.getLicenseNumber(), messages);
		validatorCategory(dto.getAuthorizedCategory().getIdAsString(), messages);
		
		return messages;
	}

	private void validateCustomerId(UUID driverId, List<Message> messages) {
		
		if(UUIDHelper.isDefaultUUID(driverId)) {
			messages.add(Message.createErrorMessage(Messages.ValidateDriver.BUSSINES_DRIVER_DOES_NOT_EXIST));
		}
	}
	
	private void validateLicenseNumber(String driverlicense, List<Message> message) {
		if(!validateLicenseNumber(driverlicense)) {
			message.add(Message.createErrorMessage(Messages.ValidateDriver.BUSSINES_DRIVER_NUMBER_LICENSE_IS_INCORRECT));
		}
	}
	
	private boolean validateLicenseNumber(String driverlicense) {
		Pattern pattern = Pattern.compile("^[A-Za-z0-9-\\+]+(\\{A-Za-z0-9-]+)$");
		Matcher mather = pattern.matcher(driverlicense);
		return mather.find();
	}
	private void validatorCategory(String category, List<Message> message) {
		if(isEmpty(category)) {
			message.add(Message.createErrorMessage(Messages.ValidateCustomer.BUSSINES_CUSTOMER_FIRST_SURNNAME_IS_EMPTY));
		}
	}
}
