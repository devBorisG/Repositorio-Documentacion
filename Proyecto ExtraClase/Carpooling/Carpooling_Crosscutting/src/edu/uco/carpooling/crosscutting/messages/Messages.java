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
	}
}
