package edu.uco.carpooling.controller.validator.routerequest;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.domain.RouteRequestDTO;

public class CreateRouteRequestValidator implements Validator<RouteRequestDTO> {

	@Override
	public List<Message> validate(RouteRequestDTO dto) {
		List<Message> messages = new ArrayList<>();

		validateCustomerId(dto.getCustomer().getId(), messages);
		validateDate(dto.getServiceRequestDate(), messages);
		validateTime(dto.getServiceRequestTime(), messages);
		return messages;
	}

	private void validateCustomerId(UUID customerId, List<Message> messages) {

		if (UUIDHelper.isDefaultUUID(customerId)) {
			messages.add(Message
					.createErrorMessage(Messages.CreateRouteRequestValidator.ERRROR_ROUTE_REQUEST_ID_IS_DEFAULT));
		}
	}

	private void validateDate(Date date, List<Message> messages) {
		if (date.before(new Date())) {
			messages.add(Message
					.createErrorMessage(Messages.CreateRouteRequestValidator.ERROR_DATE_IS_LESS_THAN_ACTUALLY_DATE));
		}
	}

	@SuppressWarnings("deprecation")
	private void validateTime(Time time, List<Message> messages) {
		if ((time.getHours() < (new Date().getHours())) && (time.getMinutes() < (new Date().getMinutes()))) {
			messages.add(Message
					.createErrorMessage(Messages.CreateRouteRequestValidator.ERROR_HOUR_IS_LESS_THAN_ACTUALLY_HOUR));
		}
	}

}
