package edu.uco.budget.data.daofactory;

import edu.uco.budget.crosscutting.exceptions.SqlException;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.BudgetDAO;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.enumeration.DAOFactoryType;

public abstract class DAOFactory {
//SI ES UNA CLASE ABSTRACTA QUIERE DECIR QUE NO PUEDE SER INSTANCIADA
	public static final DAOFactory getDAOFactory(final DAOFactoryType factory) {

		DAOFactory daoFactory;

		switch (factory) {
		case SQLSERVER:
			daoFactory = new SqlServerDAOFactory();
			break;
		case CASSANDRA:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_CASSANDRA_NOT_IMPLEMENTED);
		case MARIADB:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_MARIADB_NOT_IMPLEMENTED);
		case MONGODB:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_MONGODB_NOT_IMPLEMENTED);
		case MYSQL:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_MYSQL_NOT_IMPLEMENTED);
		case ORACLE:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_ORACLE_NOT_IMPLEMENTED);
		case POSTGRESQL:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_POSTGRESQL_NOT_IMPLEMENTED);
		default:
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_UNEXPECTED_DAOFACTORY);
		}

		return daoFactory;
	}

	protected abstract void openConnection();

	public abstract void initTransaction();

	public abstract void confirmTransaction();

	public abstract void cancelTransaction();

	public abstract void closeConnection();

	public abstract BudgetDAO getBudgetDAO();

	public abstract YearDAO getYearDAO();

	public abstract PersonDAO getPersonDAO();
}
