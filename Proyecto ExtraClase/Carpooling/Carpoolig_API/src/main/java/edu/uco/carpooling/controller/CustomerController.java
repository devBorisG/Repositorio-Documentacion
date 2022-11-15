package edu.uco.carpooling.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.uco.carpooling.controller.validator.customer.CreateCustomerValidator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.CustomerDTO;
import edu.uco.carpooling.service.command.CreatecustomerCommand;
import edu.uco.carpooling.service.command.GetCustomerByIdCommand;
import edu.uco.carpooling.service.command.implementation.CreatecustomerCommandImpl;
import edu.uco.carpooling.service.command.implementation.GetCustomerByIdCommandImpl;
import edu.uco.carpooling.crosscutting.messages.Messages;

@RestController
@RequestMapping("/carpooling/customer")
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
			
			if(messages.isEmpty()) {
				createCustomer.execute(customer);
				final List<CustomerDTO> data = new ArrayList<>();
				data.add(customer);
				response.setData(data); 
				
				response.addSuccessMessages(Messages.CustomerController.CONTROLLER_CREATE_CUSTOMER_SUCCESFUL);
			}else {
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
	ResponseEntity<Response<CustomerDTO>> getCustomerById(@PathVariable(required = true) String id) {
		Response<CustomerDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		try {
			GetCustomerByIdCommand command = new GetCustomerByIdCommandImpl();
			List<CustomerDTO> dto = command.getById(id);
			if(dto.isEmpty()) {
				throw DataCarpoolingException.createTechnicalException(Messages.CustomerController.CONTROLLER_ERROR_TRY_FOUND_CUSTOMER_BYID);
			}else {
				response.setData(dto);
				httpStatus = HttpStatus.OK;
				response.addSuccessMessages(Messages.CustomerController.CONTROLLER_SUCCES_FOUND_CUSTOMER_BYID);				
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
