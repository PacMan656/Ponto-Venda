
package model;

public class Venda {
    private int id;
    private int cliente;
    private String nome_cli;
    private String vendedor;
    private double total;
    private String dataVenda;
    
    public Venda(){
        
    }

    public Venda(int id, int cliente, String nome_cli, String vendedor, double total, String dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.nome_cli = nome_cli;
        this.vendedor = vendedor;
        this.total = total;
        this.dataVenda = dataVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    

    
}
