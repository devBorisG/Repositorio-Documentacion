package edu.uco.carpooling.crosscutting.exceptions;

public class SqlException extends DataCarpoolingException{

	private static final long serialVersionUID = 4406771349157460221L;

	public SqlException(Exception rootException,String technicalMessage, String userMessage) {
		super(rootException, technicalMessage, userMessage);
	}
	
	public static DataCarpoolingException create(String message) {
		return new SqlException(new Exception(), message, message);
	}
	
	public static DataCarpoolingException create(String technicalMessage, Exception exception) {
		return new SqlException(exception, technicalMessage, technicalMessage);
	}
	
	public static DataCarpoolingException create(String userMessage, String technicalMessage, Exception rootException) {
		return new SqlException(rootException, technicalMessage, userMessage);
	}
}
