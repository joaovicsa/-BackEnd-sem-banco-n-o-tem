package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Usuario
 */
public class PessoaJuridicaDAO {

    ConectorBD cnx = new ConectorBD();

    public PessoaJuridica getPessoa(Integer id) throws SQLException {
        ResultSet rs = cnx.getSelect("select\n"
                + "	Pessoa_Juridica.idPessoa_Juridica as id,\n"
                + "	Pessoa_Juridica.cnpj,\n"
                + "	p.nome,\n"
                + "	p.logradouro,\n"
                + "	p.cidade,\n"
                + "	p.estado,\n"
                + "	p.telefone,\n"
                + "	p.email\n"
                + "	from Pessoa_Juridica\n"
                + "INNER JOIN Pessoa as p on Pessoa_Juridica.idPessoa = p.id_Pessoa\n"
                + "WHERE\n"
                + "	Pessoa_Juridica.idPessoa = " + id.toString());

        rs.next();
        PessoaJuridica p = new PessoaJuridica(
                rs.getString("cnpj"),
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("logradouro"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email")
        );
        p.exibir();
        cnx.close();
        return p;
    }

    public ArrayList<PessoaJuridica> getPessoas() throws SQLException {
        ArrayList<PessoaJuridica> list = new ArrayList<PessoaJuridica>();

        ResultSet rs = cnx.getSelect("select\n"
                + "	Pessoa_Juridica.idPessoa_Juridica as id,\n"
                + "	Pessoa_Juridica.cnpj,\n"
                + "	p.nome,\n"
                + "	p.logradouro,\n"
                + "	p.cidade,\n"
                + "	p.estado,\n"
                + "	p.telefone,\n"
                + "	p.email\n"
                + "	from Pessoa_Juridica\n"
                + "INNER JOIN Pessoa as p on Pessoa_Juridica.idPessoa = p.id_Pessoa;");

        while (rs.next()) {
            PessoaJuridica p = new PessoaJuridica(
                    rs.getString("cnpj"),
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email")
            );
            list.add(p);
        }
        cnx.close();
        return list;
    }

    public void incluir(PessoaJuridica p) throws SQLException {
        String sqlInsertPessoa = String.format(
                "insert into pessoa ( nome, logradouro, cidade, estado, telefone, email ) values (  '%s', '%s', '%s', '%s', '%s',  '%s' );",
                p.getNome(), p.getLogradouro(), p.getCidade(), p.getEstado(), p.getTelefone(), p.getEmail());
        System.out.println(sqlInsertPessoa);

        int idNovaPessoa = cnx.insert(sqlInsertPessoa);
        if (idNovaPessoa == 0) {
            System.out.println("Erro ao criar pessoa");
        } else {

            String sqlInsertPessoaJuridica = String.format("insert into Pessoa_Juridica ( idPessoa_Juridica,cnpj,	idPessoa ) values (%s, '%s', %s);",
                    p.getId(), p.getCnpj(), idNovaPessoa);
            System.out.println(sqlInsertPessoaJuridica);
            cnx.insert(sqlInsertPessoaJuridica);
        }

    }

    public void alterar(PessoaJuridica novaPessoa) throws SQLException {

        String sqlUpdatePessoaJuridica = String.format(
                "update Pessoa_Juridica  set cnpj = '%s' where Pessoa_Juridica.idPessoa_Juridica  = %s;",
                novaPessoa.getCnpj(), novaPessoa.getId()
        );
        System.out.println(sqlUpdatePessoaJuridica);
        cnx.update(sqlUpdatePessoaJuridica);

        ResultSet rs = cnx.getSelect("select pj.idPessoa from Pessoa_Juridica pj WHERE idPessoa_Juridica = " + novaPessoa.getId() + ";");
        rs.next();

        int idPessoaAssociadaA_PessoaJuridica = rs.getInt(1);

        String sqlUpdatePessoa = String.format(
                "update pessoa set nome = '%s', logradouro = '%s', cidade = '%s', estado='%s', telefone = '%s',  email = '%s'  WHERE id_Pessoa = %s;",
                novaPessoa.getNome(), novaPessoa.getLogradouro(), novaPessoa.getCidade(), novaPessoa.getEstado(), novaPessoa.getTelefone(),
                novaPessoa.getEmail(), idPessoaAssociadaA_PessoaJuridica
        );
        System.out.println(sqlUpdatePessoa);

        cnx.update(sqlUpdatePessoa);

    }

    public void excluir(Pessoa p) throws SQLException {
        ResultSet rs = cnx.getSelect("select idPessoa from Pessoa_Juridica pj  WHERE  pj.idPessoa_Juridica = " + p.getId() + ";");
        rs.next();
        int idPessoaAssociadaA_PessoaJuridica = rs.getInt(1);
        String sqlDeletePessoaJuridica = "Delete from Pessoa_Juridica where idPessoa_Juridica = " + p.getId() + ";";
        cnx.update(sqlDeletePessoaJuridica);
        String sqlDeletePessoa = "Delete from Pessoa where id_Pessoa = " + idPessoaAssociadaA_PessoaJuridica + ";";
        cnx.update(sqlDeletePessoa);
    }

    public void close() throws SQLException {
        cnx.close();
    }
}
