/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class ClienteControllerTest {
    
    @Mock
    private ClienteController clienteControllerMock;
    
    public ClienteControllerTest() {
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
    public void testCadastrarCliente() throws ParseException {
        boolean resultadoEsperado = true;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Cliente cliente = new Cliente(1, "Bob", sdf.parse("10/10/2000"), "bob@email.com", "11122233344", "Rua Legal 10", "54999991234");
        
        Mockito.when(clienteControllerMock.cadastrarCliente("Bob", "10/10/2000", "bob@email.com", "11122233344", "Rua Legal 10", "54999991234")).thenReturn(resultadoEsperado);
        
        boolean resultado = clienteControllerMock.cadastrarCliente("Bob", "10/10/2000", "bob@email.com", "11122233344", "Rua Legal 10", "54999991234");
        
        assertEquals(resultado, true);
        assertEquals("Bob", cliente.getNome());
        verify(clienteControllerMock, times(1)).cadastrarCliente("Bob", "10/10/2000", "bob@email.com", "11122233344", "Rua Legal 10", "54999991234");
    }
    
    @Test
    public void testCadastrarClienteInvalido() throws Exception {        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Mockito.when(clienteControllerMock.cadastrarCliente("", "", "", "", "", "")).thenReturn(false);
        
        boolean resultado = clienteControllerMock.cadastrarCliente("", "", "", "", "", "");
        
        assertFalse(resultado);
        verify(clienteControllerMock, times(1)).cadastrarCliente("", "", "", "", "", "");
    }
}
