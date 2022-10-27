package edu.uco.budget.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uco.budget.crosscutting.exceptions.SqlException;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.sqlserver.BudgetSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.PersonSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.YearSqlServerDAO;

final class SqlServerDAOFactory extends DAOFactory{
	
	private Connection connection;
	
	SqlServerDAOFactory() {
		openConnection();
	}
	
	@Override
	protected void openConnection(){
		final String url = "jdbc:sqlserver://rg-wf.database.windows.net:1433;"
				+ "database=db-budget;"
				+ "user=userDmlBudget;"
				+ "password=us3rDmlBudg3t;"
				+ "encrypt=true;"
				+ "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;"
				+ "loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException exception) {
			throw SqlException.
			create(Messages.SqlServerDAOFactory.TECHNICAL_CONNECTION_NOT_POSSIBLE, exception);
		} catch (Exception exception) {
			throw SqlException.
			create(Messages.SqlServerDAOFactory.TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR, exception); //You have to catch the exceptions that we don't yet know why they occur this way
		}
	}

	@Override
	public void initTransaction() {
		 // TODO realize custom exception
	}

	@Override
	public void confirmTransaction() {
		 try {
			connection.commit();
		} catch (SQLException exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CONFIRM_TRANSACTION_NOT_POSSIBLE,exception);
		} catch (Exception exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR,exception);
		}
		
	}

	@Override
	public void cancelTransaction() {
		 try {
			connection.rollback();
		} catch (SQLException exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CANCEL_TRANSACTION_NOT_POSSIBLE, exception); 
		} catch (Exception exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR,exception);
		}
		
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CLOSE_CONNECTION_IS_NOT_POSSIBLE, exception);
		} catch (Exception exception) {
			throw SqlException
			.create(Messages.SqlServerDAOFactory.TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR,exception);
		}
		
	}

	@Override
	public BudgetDAO getBudgetDAO() {
		return new BudgetSqlServerDAO(connection);
	}

	@Override
	public YearDAO getYearDAO() {
		return new YearSqlServerDAO(connection);
	}

	@Override
	public PersonDAO getPersonDAO() {
		return new PersonSqlServerDAO(connection);
	}
	
	
}
