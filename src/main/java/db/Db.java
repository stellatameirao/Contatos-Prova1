
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Aula;
import model.AulaDto;

public class Db {

	private static Db instance = null;
	private Connection connection = null;

	private String driver;
	private String url;
	private String user;
	private String password;

	private Db() {
		this.confDB();
		this.conectar();
		this.criarTabela();
	}

	public static Db getInstance() {
		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}

	private void confDB() {
		try {
			this.driver = "org.h2.Driver";
			this.url = "jdbc:h2:~/testdb";
			this.user = "sa";
			this.password = "";
			Class.forName(this.driver);
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
	}

	// Inicia a conexão com o banco de dados
	private void conectar() {
		try {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
		}
	}

	private void criarTabela() {
		String query = "CREATE TABLE IF NOT EXISTS AULA ("
				+ "    ID BIGINT AUTO_INCREMENT PRIMARY KEY,"
				+ "    COD_DISCIPLINA INT,"
				+ "    ASSUNTO VARCHAR(255),"
				+ "    DURACAO INT,"
				+ "    DATA VARCHAR(20),"
				+ "    HORARIO VARCHAR(20)"
				+ ")";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			this.connection.commit();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	// Encerra a conexão
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());	
		}
	}
	
	//Para poder encerrar o Statement
		private static void closeStatement(Statement st) {
			if(st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
		
		//Para poder encerrar o ResultSet
		private static void closeResultSet(ResultSet rs) {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}

	/*
	 * ****************************************************************
	 * CRUD
	 * ****************************************************************
	 */

	// CRUD READ
	public ArrayList<AulaDto> findAll() {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "SELECT ID, COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO FROM AULA;";
			ArrayList<AulaDto> lista = new ArrayList<AulaDto>();
			try {
				ps = connection.prepareStatement(query);
				rs= ps.executeQuery();


				while(rs.next()) {
					Aula aula = instantiateAula(rs);
					AulaDto aulaDto =new  AulaDto(aula);
					lista.add(aulaDto);
				}

			return lista;
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}  finally {
		        closeResultSet(rs);
		        closeStatement(ps);
		    }
	}
	
}