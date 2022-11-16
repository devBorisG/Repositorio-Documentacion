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
import edu.uco.carpooling.controller.validator.routerequest.CreateRouteRequestValidator;
import edu.uco.carpooling.crosscutting.exception.CarpoolingCustomException;
import edu.uco.carpooling.crosscutting.messages.Message;
import edu.uco.carpooling.domain.RouteRequestDTO;
import edu.uco.carpooling.service.command.CreateRouteRequestCommand;
import edu.uco.carpooling.service.command.GetAllRouteRequestsCommand;
import edu.uco.carpooling.service.command.implementation.CreateRouteRequestCommandImpl;
import edu.uco.carpooling.service.command.implementation.GetAllRouteRequestsCommandImpl;

@RestController
@RequestMapping("/carpooling/routerequest")
public class RouteRequestController {

	public CreateRouteRequestCommand createRouteRequest = new CreateRouteRequestCommandImpl();
	
	@PostMapping("/")
	public ResponseEntity<Response<RouteRequestDTO>> create(@RequestBody RouteRequestDTO routeRequest){
		
		final Response<RouteRequestDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		
		try {
			Validator<RouteRequestDTO> validator = new CreateRouteRequestValidator();
			List<Message> messages = validator.validate(routeRequest);
			
			if (messages.isEmpty()) {
				createRouteRequest.execute(routeRequest);
				final List<RouteRequestDTO> data = new ArrayList<>();
				data.add(routeRequest);
				response.setData(data);
				response.addSuccessMessages("Route Request has been create succssefully");
			}else {
				httpStatus = HttpStatus.BAD_REQUEST;
				response.setMessages(messages);
			}
			
			
		} catch (final CarpoolingCustomException exception) {
			if(exception.isTechnicalException()) {
				response.addErrorMessages("There was an error trying to create Route Request. Please try again...");
				httpStatus = HttpStatus.BAD_REQUEST;
				response.addErrorMessages(exception.getMessage());
			}
		
			exception.printStackTrace();
		}
		
		catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessages(exception.getMessage());
			response.addFatalMessages("There was a unexpected error trying to create Route Request. Please try again...");
			
			exception.printStackTrace();
		}
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	
	@GetMapping("/all")
	ResponseEntity<Response<RouteRequestDTO>> get(){
		final Response<RouteRequestDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		try {
			GetAllRouteRequestsCommand command = new GetAllRouteRequestsCommandImpl();
			List<RouteRequestDTO> list = command.get();
			response.setData(list);
			httpStatus = HttpStatus.OK;
			response.addSuccessMessages("Success");
			
		}catch(CarpoolingCustomException carpoolingException) {
			response.addErrorMessages(carpoolingException.getMessage());
		}
		catch (Exception e) {
			response.addErrorMessages("There was an unexpected error trying to get request information\nMore info: ".concat(e.getMessage()));
		}
		return new ResponseEntity<Response<RouteRequestDTO>>(response, httpStatus);
	}
}
