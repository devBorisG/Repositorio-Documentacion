package edu.uco.budget.data.dao.daofactory;

import java.sql.Connection;

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
	protected void openConnection() {
		connection = null;
		// TODO Auto-generated method stub
		
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
	public BudgetDAO getBudgetDAO() {
		// TODO Auto-generated method stub
		return new BudgetSqlServerDAO(connection);
	}

	@Override
	public YearDAO getYearDAO() {
		// TODO Auto-generated method stub
		return new YearSqlServerDAO(connection);
	}

	@Override
	public PersonDAO getPersonDAO() {
		// TODO Auto-generated method stub
		return new PersonSqlServerDAO(connection);
	}
	
	
}
