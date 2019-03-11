package venda.connection;

import java.sql.Connection;

/**
 * @author Flávio Rocha
 */
public interface Connect {

	/**
	 * @return Connection
	 */
	public Connection getConnection();

	/**
	 * Fecha a conexao
	 */
	public void closeConnection();
}