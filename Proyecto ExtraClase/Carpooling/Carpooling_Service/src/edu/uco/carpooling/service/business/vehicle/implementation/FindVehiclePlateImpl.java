package edu.uco.carpooling.service.business.vehicle.implementation;

import java.util.List;

import edu.uco.carpooling.data.daofactory.DAOFactory;
import edu.uco.carpooling.domain.VehicleDTO;
import edu.uco.carpooling.service.business.vehicle.FindVehiclePlate;

public class FindVehiclePlateImpl implements FindVehiclePlate {

	private final DAOFactory factory;
	
	public FindVehiclePlateImpl(DAOFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<VehicleDTO> execute(VehicleDTO vehicle) {
		return factory.getVehicleDAO().findPlate(vehicle);
	}

}
