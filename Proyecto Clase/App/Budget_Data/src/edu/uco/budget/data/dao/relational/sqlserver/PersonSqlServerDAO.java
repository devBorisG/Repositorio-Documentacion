package edu.uco.budget.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.DataBudgetException;
import edu.uco.budget.crosscutting.messages.Messages;
import edu.uco.budget.data.dao.PersonDAO;
import edu.uco.budget.data.dao.relational.DAORelational;
import edu.uco.budget.domain.PersonDTO;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;

public final class PersonSqlServerDAO extends DAORelational implements PersonDAO {

	public PersonSqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public final void create(final PersonDTO person) {
		final var sql = "INSERT INTO Person(id, idCard, firstName, secondName, firstSurname, secondSurname) VALUES (?, ?, ?, ?, ?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setString(1, person.getIdAsString());
			preparedStatement.setString(2, person.getIdCard());
			preparedStatement.setString(3, person.getFirstName());
			preparedStatement.setString(4, person.getSecondName());
			preparedStatement.setString(5, person.getFirstSurname());
			preparedStatement.setString(6, person.getSecondSurname());

			preparedStatement.executeUpdate();

		} catch (final SQLException exception) {
			final String message = Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_CREATE_PERSON
					.concat(person.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_PERSON, exception);
		}
	}

	@Override
	public final List<PersonDTO> find(final PersonDTO person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void update(final PersonDTO person) {
		final var sql = "UPDATE Person SET idCard = ?, firstName = ?, secondName = ?, firstSurname = ?, secondSurname = ? WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, person.getIdCard());
			preparedStatement.setString(2, person.getFirstName());
			preparedStatement.setString(3, person.getSecondName());
			preparedStatement.setString(4, person.getFirstSurname());
			preparedStatement.setString(5, person.getSecondSurname());
			preparedStatement.setString(6, person.getIdAsString());

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_UPDATE_PERSON
					.concat(person.getIdAsString());
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public final void delete(final UUID id) {
		final var sql = "DELETE FROM Person WHERE id = ?";
		final var idAsString = getUUIDAsString(id);

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setString(1, idAsString);

			preparedStatement.executeUpdate();
		} catch (final SQLException exception) {
			final String message = Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_DELETE_PERSON.concat(idAsString);
			throw DataBudgetException.createTechnicalException(message, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_PERSON, exception);
		}

	}

}
