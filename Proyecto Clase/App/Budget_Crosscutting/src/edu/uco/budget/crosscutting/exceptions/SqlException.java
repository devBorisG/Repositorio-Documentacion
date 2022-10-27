package edu.uco.budget.crosscutting.exceptions;

public class SqlException extends DataBudgetException {

	private static final long serialVersionUID = -2614577065081710653L;
	
	public SqlException(Exception rootException, String technicalMessage, String userMessage) {
		super(rootException,technicalMessage,userMessage);
	}

	public static DataBudgetException create(String message) {
		return new SqlException(new Exception(), message, message);
	}
	
	public static DataBudgetException create(String technicalMessage, Exception exception) { //I am sending an Exception as a parameter because in the end everyone inherits from this
		return new SqlException(exception, technicalMessage, technicalMessage);
	}
	
	public static DataBudgetException create(String userMessage ,String technicalMessage, Exception exception) { //The same as above
		return new SqlException(exception, technicalMessage, userMessage);
	}
}
