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

public class PessoaJuridicaDAO {

	PessoaJuridica pessoaJuridica = new PessoaJuridica(0, null, null, null, null, null, null, null);

	Scanner leia = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement preparedStatemet = null;

	public void incluir(PessoaJuridica pessoaJuridica) {
		String sql = "INSERT INTO pessoajuridica (nome, logradouro, cidade, estado, telefone, email,cnpj) VALUES (?,?,?,?,?,?,?)";

		try {
			// Criar uma conexão com o banco de dados
			conn = ConectorBD.createConnectionToMySQL();

			// Criar uma preparedStatemet, para execultar uma query
			preparedStatemet = conn.prepareStatement(sql);

			preparedStatemet.setString(1, pessoaJuridica.getNome());
			preparedStatemet.setString(2, pessoaJuridica.getLogradouro());
			preparedStatemet.setString(3, pessoaJuridica.getCidade());
			preparedStatemet.setString(4, pessoaJuridica.getEstado());
			preparedStatemet.setString(5, pessoaJuridica.getTelefone());
			preparedStatemet.setString(6, pessoaJuridica.getEmail());
			preparedStatemet.setString(7, pessoaJuridica.getCnpj());

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

	public List<PessoaJuridica> getPessoas() throws ClassNotFoundException, SQLException {

		String sql = "SELECT * FROM pessoajuridica";

		List<PessoaJuridica> pessoaJuridicas = new ArrayList<>();

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
				PessoaJuridica pessoa = new PessoaJuridica(0, null, null, null, null, null, null, null);
				pessoa.setId(resultSet.getInt("idPj"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setLogradouro(resultSet.getString("logradouro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setCnpj(resultSet.getNString("cnpj"));
				pessoaJuridicas.add(pessoa);
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
		return pessoaJuridicas;
	}

	public PessoaJuridica getPessoa(int id) throws ClassNotFoundException, SQLException {

		// Criar uma conexão com o banco de dados
		conn = ConectorBD.createConnectionToMySQL();

		PessoaJuridica pessoa = new PessoaJuridica(0, null, null, null, null, null, null, null);
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM pessoaJuridica WHERE idPj=?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pessoa.setId(resultSet.getInt("idPj"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setLogradouro(resultSet.getString("logradouro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setCnpj(resultSet.getString("cnpj"));
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

	public void alterar(PessoaJuridica pessoaJuridica) throws ClassNotFoundException {

		String sql = "UPDATE pessoajuridica SET nome=?, logradouro=?, cidade=?, estado=?, telefone=? , email=?, cnpj=? WHERE idPj=?";

		Connection conn = null;

		PreparedStatement preparedStatemet = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConectorBD.createConnectionToMySQL();
			// Classe para execultar a query
			preparedStatemet = (PreparedStatement) conn.prepareStatement(sql);

			preparedStatemet.setString(1, pessoaJuridica.getNome());
			preparedStatemet.setString(2, pessoaJuridica.getLogradouro());
			preparedStatemet.setString(3, pessoaJuridica.getCidade());
			preparedStatemet.setString(4, pessoaJuridica.getEstado());
			preparedStatemet.setString(5, pessoaJuridica.getTelefone());
			preparedStatemet.setString(6, pessoaJuridica.getEmail());
			preparedStatemet.setString(7, pessoaJuridica.getCnpj());

			// O ID da pessoa que deseja atualizar
			preparedStatemet.setInt(8, pessoaJuridica.getId());

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

	public PessoaJuridica excluir(int id) throws ClassNotFoundException, SQLException {

		// Criar uma conexão com o banco de dados
		conn = ConectorBD.createConnectionToMySQL();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM pessoajuridica WHERE idPj=?");
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
		return pessoaJuridica;
	}

	public void inserirPessoa() {
		System.out.println("Nome: ");
		String nome = leia.nextLine();
		pessoaJuridica.setNome(nome);
		System.out.println("Logradouro: ");
		String logradouro = leia.nextLine();
		pessoaJuridica.setLogradouro(logradouro);
		System.out.println("Cidade: ");
		String cidade = leia.nextLine();
		pessoaJuridica.setCidade(cidade);
		System.out.println("Estado: ");
		String estado = leia.nextLine();
		pessoaJuridica.setEstado(estado);
		System.out.println("Telefone: ");
		String telefone = leia.nextLine();
		pessoaJuridica.setTelefone(telefone);
		System.out.println("Email: ");
		String email = leia.nextLine();
		pessoaJuridica.setEmail(email);
		System.out.println("CNPJ: ");
		String cnpj = leia.nextLine();
		pessoaJuridica.setCnpj(cnpj);

		incluir(pessoaJuridica);
		System.out.println("Cadastrando Pessoa Juridica");
	}

	// Mostra apenas um usuário
	public void exibirPessoa() throws ClassNotFoundException, SQLException {
		System.out.println("Digite o ID da pessoa que deseja exibir: ");
		int id = leia.nextInt();
		PessoaJuridica pessoa = getPessoa(id);
		System.out.println("=====================================");
		System.out.println("ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nLogradouro: "
				+ pessoa.getLogradouro() + "\nCidade: " + pessoa.getCidade() + "\nTelefone: " + pessoa.getTelefone()
				+ "\nEstado: " + pessoa.getEstado() + "\nEmail: " + pessoa.getEmail() + "\nCNPJ: " + pessoa.getCnpj());

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
		for (PessoaJuridica pessoa : getPessoas()) {
			System.out.println("=====================================");
			System.out.println(
					"ID: " + pessoa.getId() + "\nNome: " + pessoa.getNome() + "\nLogradouro: " + pessoa.getLogradouro()
							+ "\nCidade: " + pessoa.getCidade() + "\nTelefone: " + pessoa.getTelefone() + "\nEstado: "
							+ pessoa.getEstado() + "\nEmail: " + pessoa.getEmail() + "\nCNPJ: " + pessoa.getCnpj());

			System.out.println("=====================================");
		}
	}

	// Auterar pessoa
	public void atualizarPessoa() throws ClassNotFoundException {

		PessoaJuridica pessoa = new PessoaJuridica(0, null, null, null, null, null, null, null);
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

		System.out.println("CNPJ: ");
		String cnpj = leia.nextLine();
		pessoa.setCnpj(cnpj);

		alterar(pessoa);
		System.out.println("Alterado com sucesso!!! ");
	}
}

