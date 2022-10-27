package edu.uco.carpooling.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import edu.uco.carpooling.crosscutting.exceptions.SqlException;

public final class SQLConnectionHelper {
	
	private SQLConnectionHelper() {
		super();
	}
	
	public static final boolean connectionIsNull(final Connection connection) {
		return ObjectHelper.isNull(connection);
	}
	
	public static final boolean connectionIsOpen(final Connection connection) {
		try {
			return !connectionIsNull(connection) && !connection.isClosed();
		} catch (SQLException exception) {
			throw SqlException.create(exception.getMessage());
		}
	}
}
