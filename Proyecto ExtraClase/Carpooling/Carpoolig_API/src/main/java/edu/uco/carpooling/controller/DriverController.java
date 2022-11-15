package edu.uco.carpooling.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.carpooling.controller.response.Response;
import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.controller.validator.driver.CreateDriverValidator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.domain.DriverDTO;
import edu.uco.carpooling.service.command.CreateDriverCommand;
import edu.uco.carpooling.service.command.GetDriverByIdCommand;
import edu.uco.carpooling.service.command.implementation.CreateDriverCommandImpl;
import edu.uco.carpooling.service.command.implementation.GetDriverByIdCommandImpl;

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
				final List<DriverDTO> data = new ArrayList<>();
				data.add(driver);
				response.setData(data);
				
				response.addSuccessMessages(Messages.CustomerController.CONTROLLER_CREATE_CUSTOMER_SUCCESFUL);
			} else {
				response.setMessages(messages);
				httpStatus = HttpStatus.BAD_REQUEST;
			}
			
				
		} catch (final CarpoolingCustomException exception) {
			if(exception.isTechnicalException()) {
				response.addErrorMessages(Messages.CustomerController.CONTROLLER_ERROR_TRY_TO_CREATE_CUSTOMER);
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				response.addErrorMessages(exception.getMessage());
			}
			exception.printStackTrace();
		}
		
		catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessages(Messages.CustomerController.CONTROLLER_UNEXPECTED_ERROR_TRY_TO_CREATE_CUSTOMER);
			
			exception.printStackTrace();
		}
		
		return new ResponseEntity<>(response, httpStatus);
	}
	@GetMapping("/byid/{id}")
	ResponseEntity<Response<DriverDTO>> getDriverById(@PathVariable(required = true) String id) {
		Response<DriverDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		try {
			GetDriverByIdCommand command = new GetDriverByIdCommandImpl();
			List<DriverDTO> dto = command.getById(id);
			if(dto.isEmpty()) {
				throw DataCarpoolingException.createTechnicalException("No driver found");
			}else {
				response.setData(dto);
				httpStatus = HttpStatus.OK;
				response.addSuccessMessages("Success");				
			}
		} catch(CarpoolingCustomException e) {
			response.addErrorMessages(e.getMessage());
		}
		catch (Exception e) {
			response.addErrorMessages(e.getMessage());
		}
		return new ResponseEntity<>(response, httpStatus);
		
	}
	
}
