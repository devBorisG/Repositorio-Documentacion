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
import edu.uco.carpooling.controller.validator.CreateDriverValidator;
import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.command.CreateDriverCommand;
import edu.uco.carpooling.service.command.implementation.CreateDriverCommandImpl;

@RestController
@RequestMapping("/carpooling/driver")
public class DriverController {

	public CreateDriverCommand createDriver = new CreateDriverCommandImpl();
	
	@GetMapping("/dummy")
	public DriverDTO holaMundo() {
		return new DriverDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<DriverDTO>> create(@RequestBody DriverDTO driver){
		
		final Response<DriverDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		
		try {
			Validator<DriverDTO> validator = new CreateDriverValidator();
			List<Message> messages = validator.validate(driver);
			createDriver.execute(driver);
			
			if(messages.isEmpty()) {
				createDriver.execute(driver);
			}
			
			final List<DriverDTO> data = new ArrayList<>();
			data.add(driver);
			response.setData(data);
			
			response.addSuccessMessages("Driver has been create succssefully");		
		} catch (final CarpoolingCustomException exception) {
			if(exception.isTechnicalException()) {
				response.addErrorMessages("There was an error trying to create driver. Please try again...");
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				response.addErrorMessages(exception.getMessage());
			}
			exception.printStackTrace();
		}
		
		catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessages("There was a unexpected error trying to create driver. Please try again...");
			
			exception.printStackTrace();
		}
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
}