package edu.uco.carpooling.data.daofactory;

import java.sql.Connection;

import edu.uco.carpooling.crosscutting.exception.CrosscuttingCarpoolingException;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.SQLConnectionHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
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
		//TODO realize the connection to postgreSQL
	}

	@Override
	public void initTransaction() {
		try {
			SQLConnectionHelper.initTransaction(connection);
		} catch (CrosscuttingCarpoolingException exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.PostgreSqlDAOFactory.TECHNICAL_PROBLEM_INIT_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(Messages.PostgreSqlDAOFactory.TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR, exception);
		}
		
	}

	@Override
	public void confirmTransaction() {
		try {
			SQLConnectionHelper.commitTransaction(connection);
		} catch (CrosscuttingCarpoolingException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_PROBLEM_CONFIRM_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR, exception);
		}
	}

	@Override
	public void cancelTransaction() {
		try {
			SQLConnectionHelper.rollbackTransaction(connection);
		} catch (CrosscuttingCarpoolingException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_PROBLEM_CANCEL_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR, exception);
		}
	}

	@Override
	public void closeConnection() {
		try {
			SQLConnectionHelper.closeConnection(connection);

		} catch (CrosscuttingCarpoolingException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_PROBLEM_CLOSE_CONNECTION, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR, exception);
		}
		
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
