/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author joao_
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cadastrobd.model.util.ConectorBD;

public class PessoaFisicaDAO {

	PessoaFisica pessoaFisica = new PessoaFisica(0, null, null, null, null, null, null, null);

	Scanner leia = new Scanner(System.in);

	static Connection conn = null;
	static PreparedStatement preparedStatemet = null;

	public void incluir(PessoaFisica pessoaFisica) {
		String sql = "INSERT INTO pessoafisica (nome, logradouro, cidade, estado, telefone, email,cpf) VALUES (?,?,?,?,?,?,?)";

		try {
			// Criar uma conexão com o banco de dados
			conn = ConectorBD.createConnectionToMySQL();

			// Criar uma preparedStatemet, para execultar uma query
			preparedStatemet = conn.prepareStatement(sql);

			preparedStatemet.setString(1, pessoaFisica.getNome());
			preparedStatemet.setString(2, pessoaFisica.getLogradouro());
			preparedStatemet.setString(3, pessoaFisica.getCidade());
			preparedStatemet.setString(4, pessoaFisica.getEstado());
			preparedStatemet.setString(5, pessoaFisica.getTelefone());
			preparedStatemet.setString(6, pessoaFisica.getEmail());
			preparedStatemet.setString(7, pessoaFisica.getCpf());

			// Executar a query
			preparedStatemet.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexões
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<PessoaFisica> getPessoas() throws ClassNotFoundException, SQLException {

		String sql = "SELECT * FROM pessoafisica";

		List<PessoaFisica> pessoaFisica = new ArrayList<>();

		Connection conn = null;

		PreparedStatement preparedStatemet = null;

		ResultSet resultSet = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConectorBD.createConnectionToMySQL();

			preparedStatemet = (PreparedStatement) conn.prepareStatement(sql);

			// Classe que irá receber os dados do banco ***SELECT
			resultSet = preparedStatemet.executeQuery();

			while (resultSet.next()) {
				PessoaFisica pessoa = new PessoaFisica(0, null, null, null, null, null, null, null);
				pessoa.setId(resultSet.getInt("idPf"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setLogradouro(resultSet.getString("logradouro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setCpf(resultSet.getString("cpf"));
				pessoaFisica.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatemet != null) {
					preparedStatemet.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoaFisica;
	}

	public PessoaFisica getPessoa(int id) throws ClassNotFoundException, SQLException {

		// Criar uma conexão com o banco de dados
		conn = ConectorBD.createConnectionToMySQL();

		PessoaFisica pessoa = new PessoaFisica(0, null, null, null, null, null, null, null);
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pessoafisica WHERE idPf=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pessoa.setId(resultSet.getInt("idPf"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setLogradouro(resultSet.getString("logradouro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setCpf(resultSet.getString("cpf"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatemet != null) {
					preparedStatemet.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoa;
	}

	public void alterar(PessoaFisica pessoaFisica) throws ClassNotFoundException {

		String sql = "UPDATE pessoafisica SET nome=?, logradouro=?, cidade=?, estado=?, telefone=? , email=?, cpf=? WHERE idPf=?";

		Connection conn = null;

		PreparedStatement preparedStatemet = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConectorBD.createConnectionToMySQL();
			// Classe para execultar a query
			preparedStatemet = (PreparedStatement) conn.prepareStatement(sql);

			preparedStatemet.setString(1, pessoaFisica.getNome());
			preparedStatemet.setString(2, pessoaFisica.getLogradouro());
			preparedStatemet.setString(3, pessoaFisica.getCidade());
			preparedStatemet.setString(4, pessoaFisica.getEstado());
			preparedStatemet.setString(5, pessoaFisica.getTelefone());
			preparedStatemet.setString(6, pessoaFisica.getEmail());
			preparedStatemet.setString(7, pessoaFisica.getCpf());

			// O ID da pessoa que deseja atualizar
			preparedStatemet.setInt(8, pessoaFisica.getId());

			// Executar a query
			preparedStatemet.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatemet != null) {
					preparedStatemet.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public PessoaFisica excluir(int id) throws ClassNotFoundException, SQLException {

		// Criar uma conexão com o banco de dados
		conn = ConectorBD.createConnectionToMySQL();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM pessoafisica WHERE idPf=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatemet != null) {
					preparedStatemet.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoaFisica;
	}

	public void inserindoPessoa() {
		System.out.println("Nome: ");
		String nome = leia.nextLine();
		pessoaFisica.setNome(nome);
		System.out.println("Logradouro: ");
		String logradouro = leia.nextLine();
		pessoaFisica.setLogradouro(logradouro);
		System.out.println("Cidade: ");
		String cidade = leia.nextLine();
		pessoaFisica.setCidade(cidade);
		System.out.println("Estado: ");
		String estado = leia.nextLine();
		pessoaFisica.setEstado(estado);
		System.out.println("Telefone: ");
		String telefone = leia.nextLine();
		pessoaFisica.setTelefone(telefone);
		System.out.println("Email: ");
		String email = leia.nextLine();
		pessoaFisica.setEmail(email);
		System.out.println("CPF: ");
		String cpf = leia.nextLine();
		pessoaFisica.setCpf(cpf);

		incluir(pessoaFisica);
		System.out.println("Cadastrando Pessoa Fisica");

	}

	// Mostra apenas uma pessoa
	public void exibirPessoa() throws ClassNotFoundException, SQLException {
		System.out.println("Digite o ID da pessoa que deseja exibir: ");
		int id = leia.nextInt();
		PessoaFisica pessoa = getPessoa(id);
		System.out.println("=====================================");
		System.out.println("ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nLogradouro: "
				+ pessoa.getLogradouro() + "\nCidade: " + pessoa.getCidade() + "\nTelefone: " + pessoa.getTelefone()
				+ "\nEstado: " + pessoa.getEstado() + "\nEmail: " + pessoa.getEmail() + "\nCPF: " + pessoa.getCpf());

		System.out.println("Exibindo Pessoa Fisica através do Id ");
		System.out.println("=====================================");
	}

	// Excluir pessoa através do ID
	public void excluirPessoa() throws ClassNotFoundException, SQLException {
		System.out.println("Digite o ID da pessoa que deseja excluir: ");
		int id = leia.nextInt();
		excluir(id);
		System.out.println("Cadastro excluido");
	}

	// Mostrar todas pessoas
	public void exibirTodos() throws ClassNotFoundException, SQLException {
		for (PessoaFisica pessoa : getPessoas()) {
			System.out.println("=====================================");
			System.out.println(
					"ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nLogradouro: " + pessoa.getLogradouro()
							+ "\nCidade: " + pessoa.getCidade() + "\nTelefone: " + pessoa.getTelefone() + "\nEstado: "
							+ pessoa.getEstado() + "\nEmail: " + pessoa.getEmail() + "\nCPF: " + pessoa.getCpf());

			System.out.println("=====================================");
		}
	}

	// Auterar pessoa
	public void atualizarPessoa() throws ClassNotFoundException, SQLException {
		PessoaFisica pessoa = new PessoaFisica(0, null, null, null, null, null, null, null);
		System.out.println("Digite o Id da pessoa que deseja atualizar: ");

		System.out.println("ID: ");
		int id = leia.nextInt();
		pessoa.setId(id);
		leia.nextLine();

		System.out.println("Nome: ");
		String nome = leia.nextLine();
		pessoa.setNome(nome);

		System.out.println("Logradouro: ");
		String logradouro = leia.nextLine();
		pessoa.setLogradouro(logradouro);

		System.out.println("Estado: ");
		String estado = leia.nextLine();
		pessoa.setEstado(estado);

		System.out.println("Cidade: ");
		String cidade = leia.nextLine();
		pessoa.setCidade(cidade);

		System.out.println("Telefone: ");
		String telefone = leia.nextLine();
		pessoa.setTelefone(telefone);

		System.out.println("Email: ");
		String email = leia.nextLine();
		pessoa.setEmail(email);

		System.out.println("CPF: ");
		String cpf = leia.nextLine();
		pessoa.setCpf(cpf);

		alterar(pessoa);
		System.out.println("Alterado com sucesso!!! ");
	}
}

