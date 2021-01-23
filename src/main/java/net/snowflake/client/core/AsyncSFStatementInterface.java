package net.snowflake.client.core;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import net.snowflake.client.jdbc.SnowflakeBaseResultSet;

/** Interface for StatementHandlers that support async query execution. */
public interface AsyncSFStatementInterface extends SFStatementInterface {
  /**
   * Execute sql asynchronously
   *
   * @param sql sql statement.
   * @param parametersBinding parameters to bind
   * @param caller the JDBC interface method that called this method, if any
   * @return whether there is result set or not
   * @throws SQLException if failed to execute sql
   * @throws SFException exception raised from Snowflake components
   * @throws SQLException if SQL error occurs
   */
  SFBaseResultSet asyncExecute(
      String sql, Map<String, ParameterBindingDTO> parametersBinding, CallingMethod caller)
      throws SQLException, SFException;

  SnowflakeBaseResultSet createAsyncResultSet(SFBaseResultSet resultSet, Statement statement)
      throws SQLException;
}
