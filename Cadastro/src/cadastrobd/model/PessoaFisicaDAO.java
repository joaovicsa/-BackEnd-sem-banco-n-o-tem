package cadastrobd.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Usuario
 */
public class PessoaFisicaDAO {

    ConectorBD cnx = new ConectorBD();
    SequenceManager sequenceCode = new SequenceManager();

    public PessoaFisica getPessoa(Integer id) throws SQLException {
        ResultSet rs = cnx.getSelect("select \n"
                + //
                "\tpf.idPessoa_Fisica as id,\n"
                + //
                "\tpf.cpf,\n"
                + //
                "\tp.nome,\n"
                + //
                "\tp.logradouro,\n"
                + //
                "\tp.cidade,\n"
                + //
                "\tp.estado,\n"
                + //
                "\tp.telefone,\n"
                + //
                "\tp.email\n"
                + //
                "from Pessoa_Fisica pf\n"
                + //
                "inner join pessoa p on pf.idPessoa  = p.id_Pessoa \n"
                + //
                "\n"
                + //
                "where pf.idPessoa_Fisica  = " + id.toString());

        rs.next();
        PessoaFisica p = new PessoaFisica(
                rs.getString("cpf"),
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

    public ArrayList<PessoaFisica> getPessoas() throws SQLException {
        ArrayList<PessoaFisica> list = new ArrayList<PessoaFisica>();

        ResultSet rs = cnx.getSelect("select \n"
                + //
                "\tpf.idPessoa_Fisica as id,\n"
                + //
                "\tpf.cpf,\n"
                + //
                "\tp.nome,\n"
                + //
                "\tp.logradouro,\n"
                + //
                "\tp.cidade,\n"
                + //
                "\tp.estado,\n"
                + //
                "\tp.telefone,\n"
                + //
                "\tp.email\n"
                + //
                "from Pessoa_Fisica pf \n"
                + //
                "inner join pessoa p on pf.idPessoa  = p.id_Pessoa ");

        while (rs.next()) {
            PessoaFisica p = new PessoaFisica(
                    rs.getString("cpf"),
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

    public void incluir(PessoaFisica p) throws SQLException {
        String sqlInsertPessoa = String.format(
                "insert into pessoa ( nome, logradouro, cidade, estado, telefone, email ) values (  '%s', '%s', '%s', '%s', '%s',  '%s' );",
                p.getNome(), p.getLogradouro(), p.getCidade(), p.getEstado(), p.getTelefone(), p.getEmail());
        //System.out.println(sql);

        int idNovaPessoa = cnx.insert(sqlInsertPessoa);
        if (idNovaPessoa == 0) {
            System.out.println("Erro ao criar pessoa");
        } else {

            String sqlInsertPessoaFisica = String.format("insert into Pessoa_Fisica ( idPessoa_Fisica, cpf, idPessoa ) values (%s, '%s', %s);", p.getId(),
                    p.getCpf(), idNovaPessoa);
            //System.out.println(sqlInsertPessoaFisica);
            cnx.insert(sqlInsertPessoaFisica);
        }
    }

    public void alterar(PessoaFisica novaPessoa) throws SQLException {

        String sqlUpdatePessoaFisica = String.format(
                "update Pessoa_Fisica  set cpf= '%s' where Pessoa_fisica.idPessoa_Fisica  = %s;",
                novaPessoa.getCpf(), novaPessoa.getId()
        );
        System.out.println(sqlUpdatePessoaFisica);
        cnx.update(sqlUpdatePessoaFisica);

        ResultSet rs = cnx.getSelect("select idPessoa from Pessoa_Fisica pf  WHERE  pf.idPessoa_Fisica = " + novaPessoa.getId() + ";");
        rs.next();

        int idPessoaAssociadaA_PessoaFisica = rs.getInt(1);

        String sqlUpdatePessoa = String.format(
                "update pessoa set nome = '%s', logradouro = '%s', cidade = '%s', estado='%s', telefone = '%s',  email = '%s'  WHERE id_Pessoa = %s;",
                novaPessoa.getNome(), novaPessoa.getLogradouro(), novaPessoa.getCidade(), novaPessoa.getEstado(), novaPessoa.getTelefone(), novaPessoa.getEmail(), idPessoaAssociadaA_PessoaFisica
        );

        cnx.update(sqlUpdatePessoa);

    }

    public void excluir(Pessoa p) throws SQLException {
        ResultSet rs = cnx.getSelect("select idPessoa from Pessoa_Fisica pf  WHERE  pf.idPessoa_Fisica = " + p.getId() + ";");
        rs.next();
        int idPessoaAssociadaA_PessoaFisica = rs.getInt(1);
        String sqlDeletePessoaFisica = "Delete from Pessoa_Fisica where idPessoa_Fisica = " + p.getId() + ";";
        cnx.update(sqlDeletePessoaFisica);
        String sqlDeletePessoa = "Delete from Pessoa where id_Pessoa = " + idPessoaAssociadaA_PessoaFisica + ";";
        cnx.update(sqlDeletePessoa);
    }

    public void close() throws SQLException {
        cnx.close();
    }
}
