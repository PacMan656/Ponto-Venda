/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

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
public class ProductosTest {
    
    public ProductosTest() {
    }
    
     @Test
    void testGettersAndSetters() {
        Productos produto = new Productos(1, "P001", "Cerveja Artesanal", "Cervejaria do Vale", 50, 12.50);

        // Teste dos getters
        assertEquals(1, produto.getId());
        assertEquals("P001", produto.getCodigo());
        assertEquals("Cerveja Artesanal", produto.getNome());
        assertEquals("Cervejaria do Vale", produto.getProvedorPro());
        assertEquals(50, produto.getStock());
        assertEquals(12.50, produto.getPreco(), 0.01);

        // Teste dos setters
        produto.setId(2);
        produto.setCodigo("P002");
        produto.setNome("Vinho Tinto");
        produto.setProvedorPro("Vinícola Montanha");
        produto.setStock(30);
        produto.setPreco(45.00);

        assertEquals(2, produto.getId());
        assertEquals("P002", produto.getCodigo());
        assertEquals("Vinho Tinto", produto.getNome());
        assertEquals("Vinícola Montanha", produto.getProvedorPro());
        assertEquals(30, produto.getStock());
        assertEquals(45.00, produto.getPreco(), 0.01);
    }
}
