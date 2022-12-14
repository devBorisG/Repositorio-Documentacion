package edu.uco.carpooling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.carpooling.controller.response.Response;
import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.controller.validator.vehicle.CreateVehicleValidator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.command.CreateVehicleCommand;
import edu.uco.carpooling.service.command.implementation.CreateVehicleCommandImpl;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

	private CreateVehicleCommand createVehicleCommand = new CreateVehicleCommandImpl();

	@GetMapping("/dummy")
	public VehicleDTO holaMundo() {
		return new VehicleDTO();
	}

	@PostMapping
	public ResponseEntity<Response<VehicleDTO>> create(@RequestBody VehicleDTO vehicle) {

		final Response<VehicleDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {

			Validator<VehicleDTO> validator = new CreateVehicleValidator();
			List<Message> messages = validator.validate(vehicle);

			if (messages.isEmpty()) {
				createVehicleCommand.execute(vehicle);
				ArrayList<VehicleDTO> data = new ArrayList<>();
				data.add(vehicle);
				response.setData(data); 
				response.addSuccessMessages(Messages.VehicleController.SUCCESS_CREATE_VEHICLE);
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				response.setMessages(messages);
			}
		} catch (final CarpoolingCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			if (exception.isTechnicalException()) {
				response.addErrorMessages(Messages.VehicleController.ERROR_CREATE_VEHICLE);
			} else {
				response.addErrorMessages(exception.getMessage());
			}

			exception.printStackTrace();
		} catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessages(Messages.VehicleController.FATAL_CREATE_VEHICLE);
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, httpStatus);
	}
}
