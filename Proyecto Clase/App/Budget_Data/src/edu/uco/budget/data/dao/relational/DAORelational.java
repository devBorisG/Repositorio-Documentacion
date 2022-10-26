package edu.uco.budget.data.dao.relational;

import java.sql.Connection;

import static edu.uco.budget.crosscutting.helper.SqlConnectionHelper.connectionIsOpen;

import edu.uco.budget.crosscutting.exceptions.SqlException;
import edu.uco.budget.crosscutting.messages.Messages;

public class DAORelational {

	private Connection connection;
	
	protected DAORelational(final Connection connection) {
		
		if(!connectionIsOpen(connection)) {
			throw SqlException
			.create(Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED); 
			//The create that contains the root exception is not called because here there is no root exception that I can send, but from here I am already sending the exception
		}
		
		this.connection = connection;
		
	}
	
	protected final Connection getConnection() {
		return connection;
	}
}
