/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author marco
 */
import java.util.List;
import java.util.stream.Collectors;
import model.Cliente;
import model.Productos;
import model.Provedor;

public class BuscaUtils {

    /**
     * Método genérico para buscar em uma lista de objetos.
     * @param dados Lista de dados genéricos.
     * @param chave String de pesquisa.
     * @return Lista filtrada com os objetos que correspondem à chave de busca.
     */
    public static <T> List<T> buscarDados(List<T> dados, String chave) {
        return dados.stream()
                    .filter(item -> corresponde(item, chave))
                    .collect(Collectors.toList());
    }

    /**
     * Verifica se um objeto corresponde à chave de busca em qualquer um dos seus campos relevantes.
     * @param item Objeto a ser verificado.
     * @param chave String de pesquisa.
     * @return true se corresponder à chave, false caso contrário.
     */
    private static <T> boolean corresponde(T item, String chave) {
        // Implementação de exemplo, deve ser adaptada conforme os tipos de objetos.
        if (item instanceof Cliente cliente) {
            return cliente.getNome().toLowerCase().contains(chave.toLowerCase()) ||
                   cliente.getDni().toLowerCase().contains(chave.toLowerCase()) ||
                   cliente.getTelefone().toLowerCase().contains(chave.toLowerCase());
        } else if (item instanceof Provedor provedor) {
            return provedor.getNome().toLowerCase().contains(chave.toLowerCase()) ||
                   provedor.getRuc().toLowerCase().contains(chave.toLowerCase());
        } else if (item instanceof Productos produto) {
            return produto.getNome().toLowerCase().contains(chave.toLowerCase()) ||
                   produto.getCodigo().toLowerCase().contains(chave.toLowerCase());
        }
        return false;
    }
}

