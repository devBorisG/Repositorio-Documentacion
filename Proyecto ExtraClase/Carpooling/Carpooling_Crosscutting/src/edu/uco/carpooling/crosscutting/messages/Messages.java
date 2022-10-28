package edu.uco.carpooling.crosscutting.messages;

public class Messages {
	
	private Messages() {
		super();
	}

	public static class DAOFactory {
		
		private DAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_MONGO_DB_NOT_IMPLEMENTED="DAOFactory for MONGODB is'nt implemented yet";
		public static final String TECHNICAL_MY_SQL_NOT_IMPLEMENTED="DAOFactory for MYSQL is'nt implemented yet";
		public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED="DAOFactory for ORACLE is'nt implemented yet";
		public static final String TECHINICAL_SQL_SERVER_NOT_IMPLEMENTED="DAOFactory for SQLSERVER is'nt implemented yet";
		public static final String TECHNICAL_UNEXPECTED_DAOFACTORY= "Unexpected DAOFactory is'nt implemented";
	}
	
	public static class SqlConnectionHelper {
		
		private SqlConnectionHelper() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_IS_NULL="Connection is null";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED="Connection is closed";
		public static final String TECHNICAL_CONNECTION_ALREDY_IS_CLOSED = "Connection alredy is closed";
		public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem try close the connection. Please verify the technicals details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to start a new transaction";
		public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to start the transaction. Please verify the technical details";	
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to commit the current trasaction. Please verify te technical details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback the current trasaction. Please verify te technical details";

	}
	
	public static class PostgreSqlDAOFactory{
		
		private PostgreSqlDAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_NOT_POSSIBLE = "The connection is not possible";
		public static final String TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR = "The was an unexpected error trying openning connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying init transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying confirm transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CANCEL_TRANSACTION = "There was a problem trying to cancel transaction with the current connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying cancel transaction in PostgreSqlDAOFactory";
		public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to close connection in PostgreSqlDAOFactory";
		public static final String TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR = "There was an unexpected error trying close connection in PostgreSqlDAOFactory";
	}
}
