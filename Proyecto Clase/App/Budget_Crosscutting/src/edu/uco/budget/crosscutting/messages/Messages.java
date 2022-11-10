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
		public static final String TECHNICAL_CONNECTION_ALREDY_IS_CLOSED = "Connection alredy is closed";
		public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem try close the connection. Please verify the technicals details";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to start a new transaction";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction";
		public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";
		public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to start the transaction. Please verify the technical details";		
		public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to commit the current trasaction. Please verify te technical details";
		public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback the current trasaction. Please verify te technical details";
	}
	
	public static class SqlServerDAOFactory {
		
		private SqlServerDAOFactory() {
			super();
		}
		
		public static final String TECHNICAL_CONNECTION_NOT_POSSIBLE = "The connection is not possible";
		public static final String TECHNICAL_OPEN_CONNECTION_UNEXPECTED_ERROR = "The was an unexpected error trying openning connection in SQLServerDAOFactory";
		public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init transaction with the current connection in SQLServerDAOFactory ";
		public static final String TECHNICAL_INIT_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying init transaction in SQLServerDAOFactory";
		public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm transaction with the current connection in SQLServerDAOFactory ";
		public static final String TECHNICAL_CONFIRM_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying confirm transaction in SQLServerDAOFactory";
		public static final String TECHNICAL_PROBLEM_CANCEL_TRANSACTION = "There was a problem trying to cancel transaction with the current connection in SQLServerDAOFactory ";
		public static final String TECHNICAL_CANCEL_TRANSACTION_UNEXPECTED_ERROR = "There was an unexpected error trying cancel transaction in SQLServerDAOFactory";
		public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to close connection in SQLServerDAOFactory ";
		public static final String TECHNICAL_CLOSE_CONNECTION_UNEXPECTED_ERROR = "There was an unexpected error trying close connection in SQLServerDAOFactory";

	}
	
	public static class BudgetSqlServerDAO{
		
		private BudgetSqlServerDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_BUDGET = "There was a problem trying create budget in BudgetSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_BUDGET = "There was an unexpected problem trying create budget in BudgetSqlServer";
		public static final String TECHNICAL_PROBLEM_UPDATE_BUDGET = "There was a problem trying update budget in BudgetSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_BUDGET = "There was an unexpected problem trying update budget in BudgetSqlServer";
		public static final String TECHNICAL_PROBLEM_DELETE_BUDGET = "There was a problem trying delete budget in BudgetSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_BUDGET = "There was an unexpected problem trying delete budget in BudgetSqlServer";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS_UNEXPECTED_ERROR = "There was an unexpected problem trying recovering results from the select";
		public static final String TECHNICALPROBLEM_FILL_BUDGEDTO = "There was an unexpected problem trying to fill BudgetDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_BUDGEDTO_UNEXPECTED_PROBLEM = "There was an unexpected problem trying to fill BudgetDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_YEARDTO = "There was an unexpected problem trying to fill YearDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_YEARDTO_UNEXPECTED_PROBLEM = "There was an unexpected problem trying to fill YearDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_PERSONDTO = "There was an unexpected problem trying to fill PersonDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_PERSONDTO_UNEXPECTED_PROBLEM = "There was an unexpected problem trying to fill PersonDTO from the resultset ";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific budgets";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific budgets";
		public static final String TECHNICAL_PROBLEM_SET_PARAMETERS_VALUES = "There was a problem trying to set the parameters values to the query";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_SET_PARAMETERS_VALUES = "There was an unexpecteds problem trying to set the parameters values to the query";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying prepare the sql statement";
		
	}
	
	public static class PersonSqlServerDAO{
		
		private PersonSqlServerDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_PERSON = "There was a problem trying create person in PersonSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_PERSON = "There was an unexpected problem trying create person in PersonSqlServer";
		public static final String TECHNICAL_PROBLEM_UPDATE_PERSON = "There was a problem trying update person in PersonSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_PERSON = "There was an unexpected problem trying update person in PersonSqlServer";
		public static final String TECHNICAL_PROBLEM_DELETE_PERSON = "There was a problem trying delete person in PersonSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_PERSON = "There was an unexpected problem trying delete person in PersonSqlServer";
		public static final String TECHNICALPROBLEM_FILL_PERSONDTO = "There was an unexpected problem trying to fill PersonDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_PERSONDTO_UNEXPECTED_PROBLEM = "There was an unexpected problem trying to fill PersonDTO from the resultset ";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying prepare the sql statement";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific persons";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific persons";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS_UNEXPECTED_ERROR = "There was an unexpected problem trying recovering results from the select";
		
	}
	
	public static class YearSqlServerDAO{
		
		private YearSqlServerDAO() {
			super();
		}
		
		public static final String TECHNICAL_PROBLEM_CREATE_YEAR = "There was a problem trying create year in YearSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_YEAR= "There was an unexpected problem trying create year in YearSqlServer";
		public static final String TECHNICAL_PROBLEM_UPDATE_YEAR = "There was a problem trying update year in YearSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_YEAR = "There was an unexpected problem trying update year in YearSqlServer";
		public static final String TECHNICAL_PROBLEM_DELETE_YEAR = "There was a problem trying delete year in YearSqlServer with id=";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_YEAR = "There was an unexpected problem trying delete year in YearSqlServer";
		public static final String TECHNICALPROBLEM_FILL_YEARDTO = "There was an unexpected problem trying to fill YearDTO from the resultset ";
		public static final String TECHNICALPROBLEM_FILL_YEARDTO_UNEXPECTED_PROBLEM = "There was an unexpected problem trying to fill YearDTO from the resultset ";
		public static final String TECHNICAL_PROBLEM_PREPARED_STATEMENT = "There was a problem trying prepare the sql statement";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT = "There was an unexpected problem trying prepare the sql statement";
		public static final String TECHNICAL_PROBLEM_EXECUTE_QUERY = "There was a problem trying to execute query to find the specific years";
		public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY = "There was an unexpected problem trying to execute query to find the specific years";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS = "There was a problem recovering results from the select";
		public static final String TECHNICAL_PROBLEM_FILL_RESULTS_UNEXPECTED_ERROR = "There was an unexpected problem trying recovering results from the select";
		
		
	}
	
	public static class UUIDHelper{
		
		private UUIDHelper() {
			super();
		}
		
		public static final String TECHNICAL_UUID_FROM_STRING_INVALID = "The UUID to convert does not have a valid format";
		public static final String TECHNICAL_UUID_FROM_STRING_INVALID_UNEXPECTED_ERROR = "The was an unexpected error trying to convert a UUID from String";

	}
}
