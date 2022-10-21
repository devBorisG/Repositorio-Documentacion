package edu.uco.budget.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("No se pudo");
			 // TODO realizar custom exception
		}
	}

	@Override
	public void initTransaction() {
		 // TODO realizar custom exception
		
	}

	@Override
	public void confirmTransaction() {
		 try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace(); // TODO realizar custom exception
		}
		
	}

	@Override
	public void cancelTransaction() {
		 try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace(); // TODO realizar custom exception
		}
		
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace(); // TODO realizar custom exception
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
