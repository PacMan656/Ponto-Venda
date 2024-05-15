/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Util.BuscaUtils;
import java.util.List;
import model.Productos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author marco
 */
class BuscaUtilsTest {

    private List<Productos> produtos;

    @BeforeEach
    void setUp() {
        produtos = DadosProdutos.criarListaProdutos();  // Asume que esta função retorna uma lista pré-definida de produtos
    }

    @Test
    void testBuscarDados() {
        String chaveDeBusca = "cerveja";
        List<Productos> resultados = BuscaUtils.buscarDados(produtos, chaveDeBusca);

        // Verifica se apenas os produtos com "cerveja" no nome foram retornados
        assertFalse(resultados.isEmpty());
        assertTrue(resultados.stream().allMatch(p -> p.getNome().toLowerCase().contains(chaveDeBusca)));
        assertEquals(1, resultados.size());  // Asume que só tem 1 cerveja na lista
    }
}
