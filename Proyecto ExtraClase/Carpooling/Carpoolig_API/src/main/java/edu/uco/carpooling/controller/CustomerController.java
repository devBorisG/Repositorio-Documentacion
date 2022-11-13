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
import edu.uco.carpooling.controller.validator.CreateCustomerValidator;
import edu.uco.carpooling.controller.validator.Validator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.command.CreatecustomerCommand;
import edu.uco.carpooling.service.command.implementation.CreatecustomerCommandImpl;

@RestController
@RequestMapping("/capooling/customer")
public class CustomerController {

	public CreatecustomerCommand createCustomer = new CreatecustomerCommandImpl();
	
	@GetMapping("/dummy")
	public CustomerDTO holaMundo() {
		return new CustomerDTO();
	}
	
	@PostMapping
	public ResponseEntity<Response<CustomerDTO>> create(@RequestBody CustomerDTO customer){
		
		final Response<CustomerDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		
		try {
			Validator<CustomerDTO> validator = new CreateCustomerValidator();
			List<Message> messages = validator.validate(customer);
			createCustomer.execute(customer);
			
			if(messages.isEmpty()) {
				createCustomer.execute(customer);
			}
			
			final List<CustomerDTO> data = new ArrayList<>();
			data.add(customer);
			response.setData(data);
			
			response.addSuccessMessages("Customer has been create succssefully");		
		} catch (final CarpoolingCustomException exception) {
			if(exception.isTechnicalException()) {
				response.addErrorMessages("There was an error trying to create customer. Please try again...");
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				response.addErrorMessages(exception.getMessage());
			}
			exception.printStackTrace();
		}
		
		catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessages("There was a unexpected error trying to create customer. Please try again...");
			
			exception.printStackTrace();
		}
		
		return new ResponseEntity<>(response, httpStatus);
	}
}
