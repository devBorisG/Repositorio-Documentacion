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
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhereOrderBy(sqlBuilder, person);

		return preparedAndExecuteQuery(sqlBuilder, person);
	}

	private final List<PersonDTO> preparedAndExecuteQuery(final StringBuilder sqlBuilder, final PersonDTO person) {
		try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
			preparedStatement.setString(1, person.getIdAsString());
			return executeQuery(preparedStatement);
		} catch (DataBudgetException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_PREPARED_STATEMENT, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_PREPARED_STATEMENT, exception);
		}
	}

	private final List<PersonDTO> executeQuery(final PreparedStatement preparedStatement) {
		try (final var resultSet = preparedStatement.executeQuery()) {
			return fillResults(resultSet);
		} catch (DataBudgetException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw DataBudgetException
					.createTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_EXECUTE_QUERY, exception);
		} catch (Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTE_QUERY, exception);
		}

	}

	private final List<PersonDTO> fillResults(final ResultSet resultSet) {
		try {
			var results = new ArrayList<PersonDTO>();

			while (resultSet.next()) {
				results.add(fillPersonDTO(resultSet));
			}
			return results;
		} catch (final DataBudgetException exception) {
			throw exception;
		} catch (final SQLException exception) {
			throw DataBudgetException
					.createTechnicalException(Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_PROBLEM_FILL_RESULTS_UNEXPECTED_ERROR, exception);
		}
	}

	private final PersonDTO fillPersonDTO(final ResultSet resultSet) {
		try {
			return PersonDTO.create(resultSet.getString("IdPerson"), resultSet.getString("IdCardPerson"),
					resultSet.getString("FirstNamePerson"), resultSet.getString("SecondNamePerson"),
					resultSet.getString("FirstSurnamePerson"), resultSet.getString("SecondSurnamePerson"));
		} catch (final SQLException exception) {
			throw DataBudgetException
					.createTechnicalException(Messages.PersonSqlServerDAO.TECHNICALPROBLEM_FILL_PERSONDTO, exception);
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICALPROBLEM_FILL_PERSONDTO_UNEXPECTED_PROBLEM, exception);
		}
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT id AS IdPerson, ");
		sqlBuilder.append("idCard AS IdCardPerson, ");
		sqlBuilder.append("firstName AS FirstNamePerson, ");
		sqlBuilder.append("secondName AS SecondNamePerson, ");
		sqlBuilder.append("firstSurname AS FirstSurnamePerson, ");
		sqlBuilder.append("secondSurname AS SecondSurnamePerson, ");
		sqlBuilder.append("FROM Person ");
	}

	private final void createWhereOrderBy(final StringBuilder sqlBuilder, final PersonDTO person) {
		if (!ObjectHelper.isNull(person) && !UUIDHelper.isDefaultUUID(person.getId())) {
			sqlBuilder.append("WHERE id = ? ");
		}

		sqlBuilder.append("ORDER BY firstName ASC");
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
		} catch (final Exception exception) {
			throw DataBudgetException.createTechnicalException(
					Messages.PersonSqlServerDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_PERSON, exception);
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
