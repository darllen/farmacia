package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	public void salvar(Fabricante f) throws SQLException {
		// PASSO 1 - DEFINIR A SQL
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		// PASSO 2 - CONECTAR A SQL
		Connection conexao = ConexaoFactory.conectar();

		// PASSO 3 - TROCAR AS INTERROGAÇÕES
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		// PASSO 4 - RODAR O CÓDIGO
		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		// PASSO 1 - DEFINIR A SQL
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		// PASSO 2 - CONECTAR A SQL
		Connection conexao = ConexaoFactory.conectar();

		// PASSO 3 - TROCAR AS INTERROGAÇÕES
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		// PASSO 4 - RODAR O CÓDIGO
		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {
		// PASSO 1 - DEFINIR A SQL
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		// PASSO 2 - CONECTAR A SQL
		Connection conexao = ConexaoFactory.conectar();

		// PASSO 3 - TROCAR AS INTERROGAÇÕES
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		// PASSO 4 - RODAR O CÓDIGO
		comando.executeUpdate();
	}

	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		// PASSO 1 - DEFINIR A SQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		// PASSO 2 - CONECTAR A SQL
		Connection conexao = ConexaoFactory.conectar();

		// PASSO 3 - TROCAR AS INTERROGAÇÕES
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		// PASSO 4 - RODAR O CÓDIGO
		// VARIÁVEL QUE GUARDA RESULTADO DE CONSULTA
		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		// PASSO 1 - DEFINIR A SQL
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("OREDR BY descricao ASC ");

		// PASSO 2 - CONECTAR A SQL
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		
		return lista;
	}

	public static void main(String[] args) {
		/* TESTE DO SALVAR */
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("DESCRIÇÃO 1");
		 * 
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("DESRIÇÃO 2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.salvar(f1);
		 * fdao.salvar(f2);
		 * System.out.println("Os fabricantes foram salvos com sucesso!"); } catch
		 * (SQLException e) { e.printStackTrace();
		 * System.out.println("Ocorreu um erro ao tentar salvar um dos fabricantes!"); }
		 */
		/* TESTE DO EXCLUIR */
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(2L);
		 * 
		 * Fabricante f2 = new Fabricante(); f1.setCodigo(4L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.excluir(f1); fdao.excluir(f2);
		 * System.out.println("Os fabricantes foram removidos com sucesso!"); } catch
		 * (SQLException e) { e.printStackTrace();
		 * System.out.println("Ocorreu um erro ao tentar remover um dos fabricantes!");
		 * }
		 */

		/* TESTE DO EDITAR */
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(3L);
		 * f1.setDescricao("Descrição 3");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.editar(f1);
		 * System.out.println("O fabricante foi editado com sucesso!"); } catch
		 * (SQLException e) {
		 * 
		 * e.printStackTrace();
		 * System.out.println("Ocorreu um erro ao tentar editar o fabricante!"); }
		 */

		/* BUSCAR POR CÓDIGO */
		/*Fabricante f1 = new Fabricante();
		f1.setCodigo(3L);

		Fabricante f2 = new Fabricante();
		f1.setCodigo(5L);

		FabricanteDAO fdao = new FabricanteDAO();

		try {
			Fabricante f3 = fdao.buscarPorCodigo(f1);
			Fabricante f4 = fdao.buscarPorCodigo(f2);

			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao tentar pesquisar um dos fabricantes!");
		}*/

		FabricanteDAO fdao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = fdao.listar();
			
			for(Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro ao tentar listar os fabricantes!");
		}
		
	}
}
