package edu.uco.budget.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.budget.controller.response.Response;
import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.service.command.CreateBudgetCommand;
import edu.uco.budget.service.command.implementation.CreateBudgetCommandImpl;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

	private CreateBudgetCommand createBudgetCommand = new CreateBudgetCommandImpl();

	@GetMapping("/dummy")
	public String holaMundo() {
		return "Hola Mundo";
	}

	@PostMapping
	public ResponseEntity<Response<BudgetDTO>> create(@RequestBody BudgetDTO budget) {

		final Response<BudgetDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		
		try {
			createBudgetCommand.execute(budget);
			final List<BudgetDTO> data = new ArrayList<>();
			data.add(budget);
			
			response.addSuccessMessage("The budget has been created successfully");
			response.setData(data);
		} catch (final BudgetCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			if (exception.isTechnicalException()) {
				response.addErrorMessage("There was an error trying to create the budget. Please try again...");
			} else {
				response.addErrorMessage(exception.getMessage());
			}
			
			exception.printStackTrace();
		} catch (final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage("There was an unexpected error trying to creaate the budget. Please try again...");
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, httpStatus);
	}

}
