package edu.uco.budget.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.DataBudgetException;
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
		// TODO Auto-generated method stub
		return null;
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
