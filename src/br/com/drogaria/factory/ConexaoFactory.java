package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "a2u0r2o1r2a0@";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria?useTimezone=true&serverTimezone=UTC";

	public static Connection conectar() throws SQLException {
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	public static void main(String[] args) {
		try { // tenta fazer o comando, se der errado ele vai pro catch
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conex�o realizada com sucesso!");
		} catch (SQLException ex) { // aqui eu trato a exces�o
			ex.printStackTrace();
			System.out.println("N�o foi poss�vel realizar a conex�o!");
		}

	}
}
