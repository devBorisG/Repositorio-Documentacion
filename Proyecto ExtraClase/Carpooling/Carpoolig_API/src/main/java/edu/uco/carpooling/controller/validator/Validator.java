package edu.uco.carpooling.controller.validator;

import java.util.List;

import edu.uco.carpooling.crosscutting.messages.Message;

public interface Validator<T> {

	List<Message> validate(T dto);
}
