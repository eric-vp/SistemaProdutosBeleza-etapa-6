/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package produto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoDAOTest {
    
    @Mock
    private ProdutoDAO produtoDAOMock;
    
    public ProdutoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCadastrar() throws Exception{
        boolean resultadoEsperado = true;
        
        Produto produto = new Produto(1, "Puro Voodoo", "TMOC Parfums", "Intenso, marcante, doce, frutal e amadeirado", 199.90, 10, 5);
        
        Mockito.when(produtoDAOMock.cadastrar(produto)).thenReturn(resultadoEsperado);
        
        boolean resultado = produtoDAOMock.cadastrar(produto);
        
        assertEquals(resultado, true);
        assertEquals("Puro Voodoo", produto.getNome());
        verify(produtoDAOMock, times(1)).cadastrar(produto);
    }
}
