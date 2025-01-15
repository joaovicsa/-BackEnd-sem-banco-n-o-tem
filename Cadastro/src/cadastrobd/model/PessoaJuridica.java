/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Usuario
 */
public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(String cnpj, Integer _id, String _nome, String _logradouro, String _cidade, String _estado, String _telefone, String _email) {
        super(_id, _nome, _logradouro, _cidade, _estado, _telefone, _email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + this.getCnpj().toString());
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
