package br.edu.ifsp.dao;

import br.edu.ifsp.conexao.Conexao;
import br.edu.ifsp.modelo.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PessoaDAO {

	Connection conexao = null;
	private int id = 0;
	private List<Pessoa> pessoas = new ArrayList<>();

	public PessoaDAO() {
		conexao = Conexao.obterConexao();
	}

	public void inserirPessoa(Pessoa pessoa) {

		String SQL_insert = "INSERT INTO pessoa(nome, idade, endereco) VALUE (?,?,?)";

		try {

			PreparedStatement pstm = conexao.prepareStatement(SQL_insert);

			pstm.setString(1, pessoa.getNome());
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(3, pessoa.getEndereco());

			pstm.executeUpdate();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Erro ao executar SQL de insercao de Pessoa");
		}
	}

	public List<Pessoa> listarTodasPessoas() {
		String query = "SELECT * FROM pessoa";

		try {
			PreparedStatement pstm = conexao.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoas.add(pessoa);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Erro ao executar SQL de insercao de Pessoa");
		}

		return pessoas;
	}

	public List<Pessoa> listarPorNome(String nome) {
		String query = "SELECT * FROM pessoa WHERE nome = Upper(?);";

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			PreparedStatement pstm = conexao.prepareStatement(query);
			pstm.setString(1, nome);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");

				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoas.add(pessoa);
			}
			
			if (id == 0) {
				JOptionPane.showMessageDialog(null, nome + " não existe na base dados.");
			}
			
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Erro, não foi possível prosseguir com o cadastro.");
		}
		
		return pessoas;
	}

	public void editarPessoa(Pessoa pessoa) {

		String query = "UPDATE pessoa SET nome = ?, idade = ?, endereco = ? WHERE id = ?";

		try {

			PreparedStatement pstm = conexao.prepareStatement(query);

			pstm.setString(1, pessoa.getNome());
			pstm.setInt(2, pessoa.getIdade());
			pstm.setString(3, pessoa.getEndereco());
			pstm.setInt(4, pessoa.getId());

			pstm.executeUpdate();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Erro ao atualizar cadastro, revise os dados e tente novamente.");
		}
	}

	public void removerPessoa(Pessoa pessoa) {

		String query = "DELETE FROM pessoa WHERE id = ?";

		try {

			PreparedStatement pstm = conexao.prepareStatement(query);

			pstm.setInt(1, pessoa.getId());

			pstm.executeUpdate();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Erro ao deletar cadastro.");
		}
	}

	public void fecharConexao() {
		conexao = Conexao.fecharConexao();
	}
}
