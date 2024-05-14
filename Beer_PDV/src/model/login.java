
package model;

public class login {
    private int id;
    private String nome;
    private String usuario;
    private String pass;
    private String rol;

    public login() {
    }

    public login(int id, String nome, String usuario, String pass, String rol) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.pass = pass;
        this.rol = rol;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    
}
