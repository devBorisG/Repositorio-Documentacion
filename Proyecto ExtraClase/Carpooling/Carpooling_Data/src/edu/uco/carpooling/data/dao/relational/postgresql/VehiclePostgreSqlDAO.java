package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.data.dao.VehicleDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.VehicleDTO;

public final class VehiclePostgreSqlDAO extends DAORelational implements VehicleDAO{

	public VehiclePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final VehicleDTO vehicle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final List<VehicleDTO> find(final VehicleDTO vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void update(final VehicleDTO vehicle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void delete(final UUID id) {
		// TODO Auto-generated method stub
		
	}

}
