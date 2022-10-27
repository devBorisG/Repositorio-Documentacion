package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.data.dao.DriverDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.DriverDTO;

public final class DriverPostresSqlDAO extends DAORelational implements DriverDAO {

	public DriverPostresSqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(DriverDTO driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DriverDTO> find(DriverDTO driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DriverDTO driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}
}
