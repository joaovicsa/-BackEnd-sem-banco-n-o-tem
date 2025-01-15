/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Usuario
 */
public class Pessoa {
    private Integer id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;
    
    Pessoa(){        
        
    }
    
    Pessoa(Integer _id, String _nome, String _logradouro, String _cidade, String _estado, String _telefone, String _email){
        this.id = _id;
        this.nome = _nome;
        this.logradouro = _logradouro;
        this.cidade = _cidade;
        this.estado = _estado;
        this.telefone = _telefone;
        this.email = _email;        
    }
    
    
    
    void exibir(){
        System.out.println("id: " + this.getId().toString());
        System.out.println("nome: " + this.getNome());
        System.out.println("logradouro: " + this.getLogradouro());
        System.out.println("cidade: " + this.getCidade());
        System.out.println("estado: " + this.getEstado());
        System.out.println("telefone: " + this.getTelefone());
        System.out.println("email: " + this.getEmail());
    }    

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
