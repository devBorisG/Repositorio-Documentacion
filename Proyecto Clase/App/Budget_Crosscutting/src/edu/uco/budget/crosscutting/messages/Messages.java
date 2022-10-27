package edu.uco.budget.crosscutting.messages;

public class Messages {
	
	private Messages() {
		super();
	}
	
	public static class DAOFactory {
		
		private DAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_MONGODB_NOT_IMPLEMENTED = "DAOFactory for MONGODB is not implemented yet";
		public static final String TECHNICAL_POSTGRESQL_NOT_IMPLEMENTED = "DAOFactory for POSTGRESQL is not implemented yet";
		public static final String TECHNICAL_CASSANDRA_NOT_IMPLEMENTED = "DAOFactory for CASSANDRA is not implemented yet";
		public static final String TECHNICAL_MARIADB_NOT_IMPLEMENTED = "DAOFactory for MARIADB is not implemented yet";
		public static final String TECHNICAL_MYSQL_NOT_IMPLEMENTED = "DAOFactory for MYSQL is not implemented yet";
		public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED = "DAOFactory for ORACLE is not implemented yet";
		public static final String TECHNICAL_UNEXPECTED_DAOFACTORY = "Unexpected DAOFactory is no implemented";
		
		
	}
	
	public static class SqlConnectionHelper {
		
		private SqlConnectionHelper() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_IS_NULL = "Connetion is null";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED= "Connection is closed";

	}
	
	public static class SqlServerDAOFactory {
		
		private SqlServerDAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_CLOSE_CONNECTION_IS_NOT_POSSIBLE= "Close connection is not possible";
		public static final String TECHNICAL_CONNECTION_NOT_POSSIBLE = "Connection is not posible in this moment";
		public static final String TECHNICAL_CONFIRM_TRANSACTION_NOT_POSSIBLE = "The transaction can not be done";
		public static final String TECHNICAL_CANCEL_TRANSACTION_NOT_POSSIBLE = "Cancel transaction can not be done";
		public static final String TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR = "Open connection has an unknow error opening the connection";
		public static final String TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR = "Init Transaction has an unexpected error";
		public static final String TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR = "Confirm Transaction has an unexpected error";
		public static final String TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR = "Cancel Transaction has an unexpected error";
		public static final String TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR = "Close connection has a unexpected error closing the connection";
	}
}
