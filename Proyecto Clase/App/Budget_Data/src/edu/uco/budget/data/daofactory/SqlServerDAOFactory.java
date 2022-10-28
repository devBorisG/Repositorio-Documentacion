package edu.uco.budget.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uco.budget.crosscutting.exception.CrosscuttingBudgetException;
import edu.uco.budget.crosscutting.exception.DataBudgetException;
import edu.uco.budget.crosscutting.helper.SqlConnectionHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.sqlserver.BudgetSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.PersonSqlServerDAO;
import edu.uco.budget.data.dao.relational.sqlserver.YearSqlServerDAO;

final class SqlServerDAOFactory extends DAOFactory {

	private Connection connection;

	SqlServerDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		final String url = "jdbc:sqlserver://rg-wf.database.windows.net:1433;" + "database=db-budget;"
				+ "user=userDmlBudget;" + "password=us3rDmlBudg3t;" + "encrypt=true;" + "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;" + "loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(url);
<<<<<<< Updated upstream
		} catch (SQLException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_CONNECTION_NOT_POSSIBLE, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR, exception);
			// You have to catch the exceptions that we don't yet know why they occur this
			// way
=======
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
			 // TODO realizar custom exception
>>>>>>> Stashed changes
		}
	}

	@Override
	public void initTransaction() {
		try {
<<<<<<< Updated upstream
			SqlConnectionHelper.initTransaction(connection);
		} catch (CrosscuttingBudgetException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_PROBLEM_INIT_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR, exception);
		}

=======
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 // TODO realizar custom exception
		
>>>>>>> Stashed changes
	}

	@Override
	public void confirmTransaction() {
		try {
			SqlConnectionHelper.commitTransaction(connection);
		} catch (CrosscuttingBudgetException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_PROBLEM_CONFIRM_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR, exception);
		}

	}

	@Override
	public void cancelTransaction() {
		try {
			SqlConnectionHelper.rollbackTransaction(connection);
		} catch (CrosscuttingBudgetException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_PROBLEM_CANCEL_TRANSACTION, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR, exception);
		}
	}

	@Override
	public void closeConnection() {
		try {
			SqlConnectionHelper.closeConnection(connection);

		} catch (CrosscuttingBudgetException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_PROBLEM_CLOSE_CONNECTION, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.SqlServerDAOFactory.TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR, exception);
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
