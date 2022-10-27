package edu.uco.carpooling.data.daofactory;

import edu.uco.carpooling.crosscutting.exceptions.SqlException;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.UserDAO;
import edu.uco.carpooling.data.enumeration.DAOFactoryType;

public abstract class DAOFactory {

	public static final DAOFactory getDAOFactory(final DAOFactoryType factory) {

		DAOFactory daoFactory;

		switch (factory) {
		case POSTGRESQL: {
			daoFactory = new PostgreSqlDAOFactory();
			break;
		}
		case MONGO_DB: {
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_MONGO_DB_NOT_IMPLEMENTED);
		}
		case MY_SQL: {
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_MY_SQL_NOT_IMPLEMENTED);
		}
		case ORACLE: {
			throw SqlException.create(Messages.DAOFactory.TECHNICAL_ORACLE_NOT_IMPLEMENTED);
		}
		case SQL_SERVER: {
			throw SqlException.create(Messages.DAOFactory.TECHINICAL_SQL_SERVER_NOT_IMPLEMENTED);
		}
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

	public abstract UserDAO getUserDAO();

	// TODO create others getDAO
}
