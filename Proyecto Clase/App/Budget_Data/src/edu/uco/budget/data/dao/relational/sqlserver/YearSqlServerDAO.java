package edu.uco.budget.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.DataBudgetException;
import edu.uco.budget.crosscutting.helper.ObjectHelper;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.YearDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.YearDTO;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class YearSqlServerDAO extends DAORelational implements YearDAO {

	public YearSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final YearDTO year) {
		final var sql = "INSERT INTO Year(id,year) VALUES (?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, year.getIdAsString());
			preparedStatement.setShort(2, year.getYearNumber());

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_CREATE_YEAR.concat(year.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_YEAR, exception);
		}
	}

	@Override
	public final List<YearDTO> find(final YearDTO year) {
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhereOrderBy(sqlBuilder, year);

		return preparedAndExecuteQuery(sqlBuilder, year);
	}

	private final List<YearDTO> preparedAndExecuteQuery(final StringBuilder sqlBuilder, final YearDTO year) {
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
			preparedStatement.setString(1, year.getIdAsString());
			return executeQuery(preparedStatement);
		} catch (DataBudgetException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_PREPARED_STATEMENT, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT, exception);
		}

	}

	private final List<YearDTO> executeQuery(final PreparedStatement preparedStatement) {
		try (final var resultSet = preparedStatement.executeQuery()) {
			return fillResults(resultSet);
		} catch (DataBudgetException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataBudgetException
					.createTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY, exception);
		}
	}

	private final List<YearDTO> fillResults(final ResultSet resultSet) {
		try {
			var results = new ArrayList<YearDTO>();

			while (resultSet.next()) {
				results.add(fillYearDTO(resultSet));
			}

			return results;
		} catch (final DataBudgetException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataBudgetException.createTechnicalException(Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS,
					exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS_UNEXPECTED_ERROR, exception);
		}

	}

	private final YearDTO fillYearDTO(final ResultSet resultSet) {
		try {
			return YearDTO.create(resultSet.getString("IdYear"), resultSet.getShort("NumberYear"));
		} catch (final SQLException exception) {
			throw DataBudgetException.createTechnicalException(Messages.YearSqlServerDAO.TECHNICALPROBLEM_FILL_YEARDTO,
					exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICALPROBLEM_FILL_YEARDTO_UNEXPECTED_PROBLEM, exception);
		}
	}

	private final  void createWhereOrderBy(final StringBuilder sqlBuilder, final YearDTO year) {
		if (!ObjectHelper.isNull(year) && !UUIDHelper.isDefaultUUID(year.getId())) {
			sqlBuilder.append("WHERE id = ? ");
		}

		sqlBuilder.append("ORDER BY year ASC ");
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT id AS IdYear, ");
		sqlBuilder.append("year AS NumberYear ");
		sqlBuilder.append("FROM Year ");
	}

	@Override
	public final void update(final YearDTO year) {
		final var sql = "UPDATE Year SET year = ? WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setShort(1, year.getYearNumber());
			preparedStatement.setString(2, year.getIdAsString());

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_YEAR.concat(year.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_YEAR, exception);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM Year WHERE id = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idAsString);

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.YearSqlServerDAO.TECHNICAL_PROBLEM_DELETE_YEAR.concat(idAsString);
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.YearSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_YEAR, exception);
		}
	}

}
