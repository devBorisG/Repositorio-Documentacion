package edu.uco.carpooling.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String create(@RequestBody VehicleDTO vehicle) {
		createVehicleCommand.execute(vehicle);
		return "Creacion exitosa";
	}
}
