package edu.uco.carpooling.data.dao.relational;

import java.sql.Connection;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.SQLConnectionHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;

public class DAORelational {
	
	private Connection connection;
	
	protected DAORelational(final Connection connection) {
		
		if(!SQLConnectionHelper.connectionIsOpen(connection)) {
			throw DataCarpoolingException
			.createTechnicalException(Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED);
		}
		
		this.connection = connection;
	}

	protected final Connection getConnection() {
		return connection;
	}
}
