/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import relatorio.Relatorio;
import relatorio.RelatorioDAO;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioControllerTest {
    
    @Mock
    private RelatorioDAO relatorioDAO;
    
    public RelatorioControllerTest() {
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
    public void testPesquisarRelatorio() {
        Relatorio relatorio = new Relatorio(10, 1000);
        
        Mockito.when(relatorioDAO.gerarRelatorio(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31))).thenReturn(relatorio);
        
        RelatorioController controller = new RelatorioController(relatorioDAO);
        Relatorio resultado = controller.pesquisarRelatorio("01/01/2025", "31/12/2025");        
        
        assertNotNull(resultado);
        assertEquals(relatorio, resultado);        
    }
    
}
