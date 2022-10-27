package edu.uco.budget.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import edu.uco.budget.crosscutting.exceptions.SqlException;

public final class SqlConnectionHelper {
	
	private SqlConnectionHelper() {
		super();
	}
	
	public static final boolean connectionIsNull(final Connection connection) {
		return ObjectHelper.isNull(connection);
	}
	
	public static final boolean connectionIsOpen(final Connection connection) {
		try {
			return !ObjectHelper.isNull(connection) && !connection.isClosed();
		} catch (final SQLException exception) {
			throw SqlException.create(exception.getMessage());
		}
	}
}
