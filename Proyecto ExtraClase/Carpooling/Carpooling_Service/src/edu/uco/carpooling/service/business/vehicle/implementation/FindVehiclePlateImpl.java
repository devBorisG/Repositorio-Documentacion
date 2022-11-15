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
	public VehicleDTO execute(String plate) {
		final VehicleDTO vehicle = VehicleDTO.create(plate);
		VehicleDTO result = new VehicleDTO();

		final List<VehicleDTO> results = factory.getVehicleDAO().findPlate(vehicle);

		if(!results.isEmpty()) {
			result = results.get(0);
		}



		return result;
	}

}
