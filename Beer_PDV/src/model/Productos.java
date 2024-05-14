
package model;


public class Productos {
    private int id;
    private String codigo;
    private String nome;
    private int Provedor;
    private String ProvedorPro;
    private int stock;
    private double precio;
    
    public Productos(){
        
    }

    public Productos(int id, String codigo, String nome, int Provedor, String ProvedorPro, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.Provedor = Provedor;
        this.ProvedorPro = ProvedorPro;
        this.stock = stock;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getProvedor() {
        return Provedor;
    }

    public void setProvedor(int Provedor) {
        this.Provedor = Provedor;
    }

    public String getProvedorPro() {
        return ProvedorPro;
    }

    public void setProvedorPro(String ProvedorPro) {
        this.ProvedorPro = ProvedorPro;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   
}
