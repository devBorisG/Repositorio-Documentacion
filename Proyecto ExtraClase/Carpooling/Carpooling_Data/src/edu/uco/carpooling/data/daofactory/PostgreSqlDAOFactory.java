package edu.uco.carpooling.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uco.carpooling.crosscutting.exception.CrosscuttingCarpoolingException;
import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.SQLConnectionHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DriverDAO;
import edu.uco.carpooling.data.dao.DriverPerVehicleDAO;
import edu.uco.carpooling.data.dao.RouteRequestDAO;
import edu.uco.carpooling.data.dao.CustomerDAO;
import edu.uco.carpooling.data.dao.VehicleDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.DriverPostresSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.CustomerPostgreSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.DriverPerVehiclePostgresSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.VehiclePostgreSqlDAO;
import edu.uco.carpooling.data.dao.relational.postgresql.RouteRequestPostgreSqlDAO;


final class PostgreSqlDAOFactory extends DAOFactory {

	private Connection connection;

	public PostgreSqlDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		final String url = "jdbc:postgresql://ec2-35-170-21-76.compute-1.amazonaws.com/df7f8n649fij8i";
		final String user = "jqfqqgioigerua";
		final String pass = "95c86ffbf0eee37a4676aed43dd2f6a420fb1a7d8dd332d70e4aa28b3e5b8f41";
		try {
			connection = DriverManager.getConnection(url,user,pass);
		} catch (SQLException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_CONNECTION_NOT_POSSIBLE, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR, exception);
		}

	}

	@Override
	public void initTransaction() {
		try {
			SQLConnectionHelper.initTransaction(connection);
		} catch (CrosscuttingCarpoolingException exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_PROBLEM_INIT_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataCarpoolingException.createTechnicalException(
					Messages.PostgreSqlDAOFactory.TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR, exception);
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
	public CustomerDAO getUserDAO() {
		return new CustomerPostgreSqlDAO(connection);
	}

	@Override
	public VehicleDAO getVehicleDAO() {
		return new VehiclePostgreSqlDAO(connection);
	}

	@Override
	public DriverDAO getDriverDAO() {
		return new DriverPostresSqlDAO(connection);
	}

	@Override
	public RouteRequestDAO getRouteRequestDAO() {
		return new RouteRequestPostgreSqlDAO(connection);
	}

	@Override
	public DriverPerVehicleDAO getDriverPerVehicleDAO() {
		return new DriverPerVehiclePostgresSqlDAO(connection);
	}
	

}
