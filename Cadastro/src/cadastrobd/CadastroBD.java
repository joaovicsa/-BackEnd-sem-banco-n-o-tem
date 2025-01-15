/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;


import java.sql.SQLException;
import java.util.ArrayList;

import cadastrobd.model.Pessoa;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;

/**
 *
 * @author Usuario
 */
public class CadastroBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();

        PessoaFisica PessoaF = new PessoaFisica("00011122299", 1, "Jose da Silva",
        "Rua Americano do Brasil", "Campinas", "SP", "1137968555", "josesilva@jose.com.br");
        pfDAO.incluir(PessoaF);
       
        
    }
    
}
