package edu.uco.carpooling.data.dao.relational.postgresql;
	
	import static edu.uco.carpooling.crosscutting.helper.UUIDHelper.getUUIDAsString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.carpooling.crosscutting.exception.DataCarpoolingException;
import edu.uco.carpooling.crosscutting.helper.ObjectHelper;
import edu.uco.carpooling.crosscutting.helper.UUIDHelper;
import edu.uco.carpooling.crosscutting.messages.Messages;
import edu.uco.carpooling.data.dao.DriverDAO;
import edu.uco.carpooling.data.dao.relational.DAORelational;
import edu.uco.carpooling.domain.AuthorizedCategoryDTO;
import edu.uco.carpooling.domain.DriverDTO;
	
	public final class DriverPostresSqlDAO extends DAORelational implements DriverDAO {
	
		public DriverPostresSqlDAO(Connection connection) {
			super(connection);
		}
	
		@Override
		public void create(final DriverDTO driver) {
			final var sql = "INSERT INTO public.driver(id,licensenumber,authorizedcategories) "
					+ "VALUES (?, ?, ?)";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getIdAsString());
				preparedStatement.setString(2, driver.getLicenseNumber());
				preparedStatement.setString(3, driver.getAuthorizedCategory().getIdAsString());
	
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER
						.concat(driver.getIdAsString());
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			}
			}

		@Override
		public List<DriverDTO> find(final DriverDTO driver) {
			var sqlBuilder = new StringBuilder();
			final var parameters = new ArrayList<Object>();
			
			createSelectFrom(sqlBuilder);
			createWhere(sqlBuilder, driver, parameters);
			createOrderBy(sqlBuilder);
			
			return prepareAndExecuteQuery(sqlBuilder, parameters);
		}
		private final List<DriverDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters){
			try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())){
				
				setParameterValues(preparedStatement, parameters);
				
				return executeQuery(preparedStatement);
				
			} catch (final DataCarpoolingException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			}
		}
		
		private final void createSelectFrom(final StringBuilder sqlbuilder) {
			sqlbuilder.append("SELECT      Dr.id AS IdDriver,");
			sqlbuilder.append("            Dr.licensenumber AS license,");
			sqlbuilder.append("            Dr.authorizedcategories AS Categorie,");
			sqlbuilder.append("            Au.id AS IDCategorie,");
			sqlbuilder.append("            Au.licensenumber AS License,");
			sqlbuilder.append("            Au.authorizedcategories,");
			sqlbuilder.append("            U.Id AS UserId,");
			sqlbuilder.append("            U.dni AS DniUser,");
			sqlbuilder.append("            U.firstname AS Name,");
			sqlbuilder.append("            U.secondname AS SecondName,");
			sqlbuilder.append("            U.firstsurname AS FirstSurname,");
			sqlbuilder.append("            U.Secondsurname AS SecondSurname,");
			sqlbuilder.append("            U.phone As Phone,");
			sqlbuilder.append("            U.companyemail AS Email");
			sqlbuilder.append("FROM        driver Dr ");
			sqlbuilder.append("INNER JOIN  authorizedcategories Au,");
			sqlbuilder.append("ON          Dr.authorizedcategorie = Au.id");
			sqlbuilder.append("INNER JOIN  user U");
			sqlbuilder.append("ON          Dr.id = U.id");
		}
		
		private final void createWhere(final StringBuilder sqlBuilder, final DriverDTO driver,
				final List<Object> parameters) {
			if(!ObjectHelper.isNull(driver)) {
					var setWhere = true;
				
				if (!UUIDHelper.isDefaultUUID(driver.getId())) {
					sqlBuilder.append("WHERE Ro.id = ?");
						setWhere = false;
						parameters.add(driver.getIdAsString());
				}
				
				if (!UUIDHelper.isDefaultUUID(driver.getAuthorizedCategory().getId())) {
						sqlBuilder.append(setWhere ? "WHERE ": "AND ").append("Ro.IdDriverVehicle");
						parameters.add(driver.getAuthorizedCategory().getIdAsString());
				}
			}
		}
		
		private void createOrderBy(final StringBuilder sqlBuilder) {
			sqlBuilder.append("ORDER BY    Dr.licenseNumber");
			sqlBuilder.append("ORDER BY    U.firstName");
		}
		
		private final List<DriverDTO> executeQuery(PreparedStatement preparedStatement){
			try  (final var resultset = preparedStatement.executeQuery()){
				return fillResults (resultset);
			} catch (DataCarpoolingException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			}
		}
		
		private void setParameterValues(PreparedStatement preparedStatement, final List<Object> parameters) {
			try {
				for(int index = 0; index < parameters.size(); index ++) {
					preparedStatement.setObject(index + 1, parameters.get(index));
				}
			} catch (SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_CREATE_DRIVER, exception);
			}
		}
		
		private final List<DriverDTO> fillResults(final ResultSet resultSet){
			
			try {
				var results = new ArrayList<DriverDTO>();
				
				while(resultSet.next()) {
					results.add(fillDriverDTO(resultSet));
				}
				return results;
			} catch (final DataCarpoolingException exception) {
				throw exception;
			} catch (final SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS_DRIVER, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_DRIVER, exception);
			}
		}
		
		private final DriverDTO fillDriverDTO(final ResultSet resultSet) {
	        try {
	        	return DriverDTO.create(resultSet.getString("id"),resultSet.getString("licenseNumber"),
	        			fillAuthorizedCategoryDTO(resultSet));
	        } catch (final SQLException exception) {
	            throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS_DRIVER, exception);
	        } catch (final Exception exception) {
	            throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_DRIVER, exception);
	        }
	    }
		
		private final AuthorizedCategoryDTO fillAuthorizedCategoryDTO(final ResultSet resultSet) {
			try {
				return AuthorizedCategoryDTO.create(resultSet.getString("IdCategory"),
						resultSet.getString("NameCategory"),resultSet.getString("Validity"));
			} catch (final SQLException exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_FILL_RESULTS_DRIVER, exception);			
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILL_RESULTS_DRIVER, exception);
			}
		}

		@Override
		public void update(final DriverDTO driver) {
			final var sql = "UPDATE \"Driver\" \"SET licenseNumber\" = ?" + "\"authorizedCategories\" = ? " + "WHERE id = ?";
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)) {
				preparedStatement.setString(1, driver.getIdAsString());
				preparedStatement.setString(2, driver.getLicenseNumber());
				preparedStatement.setString(3, driver.getAuthorizedCategory().getIdAsString());
	
				preparedStatement.executeUpdate();
				
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_UPDATE_DRIVER
						.concat(driver.getIdAsString());
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_DRIVER, exception);
			}
		}
	
		@Override
		public void delete(final UUID id) {
			final var sql = "DELETE FROM \"Driver\" WHERE id = ?";
			final var idAsString = getUUIDAsString(id);
	
			try (final var preparedStatement = getConnection().prepareStatement(sql)){
				preparedStatement.setString(1, idAsString);
				
				preparedStatement.executeUpdate();
			} catch (final SQLException exception) {
				final String message = Messages.DriverPostgreSqlDAO.TECHNICAL_PROBLEM_DELETE_DRIVER.concat(idAsString);
				throw DataCarpoolingException.createTechnicalException(message, exception);
			} catch (final Exception exception) {
				throw DataCarpoolingException.createTechnicalException(
						Messages.DriverPostgreSqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_DRIVER, exception);
			}
		}

		@Override
		public List<DriverDTO> findById(String id) {
			String query = "SELECT * FROM public.driver D INNER JOIN public.user U ON D.id = U.id INNER JOIN public.authorizedcategories A on D.authorizedcategories = A.id WHERE D.id = ?";
			try (final var preparedStatement = getConnection().prepareStatement(query)) {

				preparedStatement.setString(1, id);
				List<DriverDTO> list = executeQuery(preparedStatement);
				return list;

			} catch (SQLException e) {
				throw DataCarpoolingException.createTechnicalException(e.getMessage());
			} catch (Exception e) {
				throw DataCarpoolingException.createTechnicalException(e.getMessage());
			}
		}
	}
