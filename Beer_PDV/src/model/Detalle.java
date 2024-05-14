
package model;

public class Detalle {
    private int id;
    private int id_pro;
    private int cantidad;
    private double preco;
    private int id_venda;
    
    public Detalle(){
        
    }

    public Detalle(int id, int id_pro, int cantidad, double preco, int id_venda) {
        this.id = id;
        this.id_pro = id_pro;
        this.cantidad = cantidad;
        this.preco = preco;
        this.id_venda = id_venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pro() {
        return id_pro;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }
}
