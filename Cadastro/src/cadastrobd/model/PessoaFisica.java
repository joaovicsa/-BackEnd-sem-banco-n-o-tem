/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Usuario
 */
public class PessoaFisica extends Pessoa{
    private String cpf;
    
    public PessoaFisica() {
        super();
    }
    
    public PessoaFisica(String cpf, Integer _id, String _nome, String _logradouro, String _cidade, String _estado, String _telefone, String _email) {
        super(_id, _nome, _logradouro, _cidade, _estado, _telefone, _email);
        this.cpf = cpf;
        
    }
    
    
    
    @Override
        public void exibir(){
            super.exibir();
            System.out.println("CPF: " + this.getCpf().toString());
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
