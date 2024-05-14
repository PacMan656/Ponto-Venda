
package model;

public class Provedor {
    private int id;
    private String ruc;
    private String nome;
    private String telefone;
    private String direccion;
    
    public Provedor(){
        
    }

    public Provedor(int id, String ruc, String nome, String telefone, String direccion) {
        this.id = id;
        this.ruc = ruc;
        this.nome = nome;
        this.telefone = telefone;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
