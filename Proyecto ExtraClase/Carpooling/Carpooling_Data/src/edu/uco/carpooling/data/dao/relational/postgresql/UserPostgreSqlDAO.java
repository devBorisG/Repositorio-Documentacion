package edu.uco.carpooling.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.data.dao.UserDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.UserDTO;

public final class UserPostgreSqlDAO extends DAORelational implements UserDAO{

	public UserPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final List<UserDTO> find(final UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void update(final UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void delete(final UUID id) {
		// TODO Auto-generated method stub
		
	}

}
