package cadastrobd.model;

/**
 *
 * @author joao_
 */

import java.sql.SQLException;
import java.util.Scanner;

public class CadastroBDTeste {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		PessoaFisicaDAO pessoaFisicaDao = new PessoaFisicaDAO();
		PessoaJuridicaDAO pessoaJuridicaDao = new PessoaJuridicaDAO();

		try (Scanner leia = new Scanner(System.in)) {
			while (true) {

				try {
					System.out.println("Cadastro De Pessoas");
					System.out.println();
					System.out.println("1 - Incluir Pessoa\n" + "2 - Alterar Pessoa\n" + "3 - Excluir Pessoa\n"
							+ "4 - Buscar pelo Id\n" + "5 - Exibir Todos Cadastrados\n" + "0 - Finalizar Programa ");
					System.out.println();
					System.out.println("Escolha uma das opcoes a cima: ");

					Integer opc = leia.nextInt();
					leia.nextLine();

					if (opc >= 1 && opc <= 5) {

						System.out.println("Digite:\n F - Pessoa Fisica | J - Pessoa Juridica");
						String cadast = leia.nextLine().toUpperCase();
						String cadastoL = cadast.substring(0, 1);

						try {
							switch (opc) {
							case 1:
								if (cadastoL.equals("F")) {

									pessoaFisicaDao.inserindoPessoa();

								} else if (cadastoL.equals("J")) {

									pessoaJuridicaDao.inserirPessoa();
								}
								break;
							case 2:
								if (cadastoL.equals("F")) {

									pessoaFisicaDao.exibirTodos();
									pessoaFisicaDao.atualizarPessoa();
									pessoaFisicaDao.exibirTodos();

								} else if (cadastoL.equals("J")) {

									pessoaJuridicaDao.exibirTodos();
									pessoaJuridicaDao.atualizarPessoa();
									pessoaJuridicaDao.exibirTodos();
								}
								break;
							case 3:

								if (cadastoL.equals("F")) {

									pessoaFisicaDao.excluirPessoa();
								} else if (cadastoL.equals("J")) {

									pessoaJuridicaDao.excluirPessoa();
								}
								break;
							case 4:

								if (cadastoL.equals("F")) {

									pessoaFisicaDao.exibirPessoa();

								} else if (cadastoL.equals("J")) {

									pessoaJuridicaDao.exibirPessoa();

								}
								break;
							case 5:

								if (cadastoL.equals("F")) {
									pessoaFisicaDao.exibirTodos();
									System.out.println("Exibindo todas Pessoas Fisica");
								} else if (cadastoL.equals("J")) {

									pessoaJuridicaDao.exibirTodos();
									System.out.println("Exibindo todas Pessoas Juridica");
								}
								break;

							}
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					} else if (opc == 0) {
						System.out.println("Execução finalizada");
						return;

					} else {
						System.out.println("Opção Invalida");
					}
				} catch (Exception e) {
					System.out.println("Digite uma opção valida");
				}

				while (true) {
					System.out.println("Deseja realizar mais operações? [S/N]");
					String resp = leia.nextLine().toUpperCase();
					String respoL = resp.substring(0, 1);

					if (respoL.equals("N")) {
						System.out.println("Programa encerrado");
						return;
					} else if (respoL.equals("S")) {
						break;
					}
				}

			}
		}

	}

}
