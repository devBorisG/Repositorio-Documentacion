package edu.uco.carpooling.data.daofactory;

import java.sql.Connection;

import edu.uco.carpooling.data.dao.DriverDAO;
import edu.uco.carpooling.data.dao.UserDAO;
import edu.uco.carpooling.data.dao.VehicleDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.DriverPostresSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.UserPostgreSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.VehiclePostgreSqlDAO;

final class PostgreSqlDAOFactory extends DAOFactory{

	private Connection connection;
	
	public PostgreSqlDAOFactory() {
		openConnection();
	}
	
	@Override
	protected void openConnection() {
		connection = null;
	}

	@Override
	public void initTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserPostgreSqlDAO(connection);
	}

	@Override
	public VehicleDAO getVehicleDAO() {
		return new VehiclePostgreSqlDAO(connection);
	}

	@Override
	public DriverDAO getDriverDAO() {
		return new DriverPostresSqlDAO(connection);
	}

}
