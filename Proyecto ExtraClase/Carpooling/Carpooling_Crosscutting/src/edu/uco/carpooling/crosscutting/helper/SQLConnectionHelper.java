package edu.uco.carpooling.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import edu.uco.carpooling.crosscutting.exception.CrosscuttingCarpoolingException;
import edu.uco.carpooling.crosscutting.messages.Messages;

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
			throw CrosscuttingCarpoolingException
					.createTechnicalException(Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED, exception);
		}
	}

	public static final void closeConnection(final Connection connection) {
		try {
			if (!connectionIsOpen(connection)) {
				throw CrosscuttingCarpoolingException
						.createTechnicalException(Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_ALREDY_IS_CLOSED);
			}

			connection.close();

		} catch (final CrosscuttingCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw CrosscuttingCarpoolingException.createTechnicalException(
					Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_CLOSING_CONNECTION, exception);
		}
	}

	public static final void initTransaction(final Connection connection) {
		try {
			if (!connectionIsOpen(connection)) {
				throw CrosscuttingCarpoolingException.createTechnicalException(
						Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION);
			}

			connection.setAutoCommit(false);

		} catch (CrosscuttingCarpoolingException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw CrosscuttingCarpoolingException.createTechnicalException(
					Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION, exception);
		}
	}

	public static final void commitTransaction(final Connection connection) {
		try {
			if (!connectionIsOpen(connection)) {
				throw CrosscuttingCarpoolingException.createTechnicalException(
						Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION);
			}

			connection.commit();

		} catch (final CrosscuttingCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw CrosscuttingCarpoolingException.createTechnicalException(
					Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION, exception);
		}
	}

	public static final void rollbackTransaction(final Connection connection) {
		try {
			if (!connectionIsOpen(connection)) {
				throw CrosscuttingCarpoolingException.createTechnicalException(
						Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION);
			}

			connection.rollback();

		} catch (final CrosscuttingCarpoolingException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw CrosscuttingCarpoolingException.createTechnicalException(
					Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION, exception);
		}
	}
}
