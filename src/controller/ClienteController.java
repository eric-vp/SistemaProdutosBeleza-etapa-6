/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author ericv
 */
public class ClienteController {
    public boolean cadastrarCliente(String nome, String dataNascimentoStr, String email, String cpf, String endereco, String telefone) {
        if (nome.isBlank() || dataNascimentoStr.isBlank() || email.isBlank() || cpf.isBlank() || endereco.isBlank() || telefone.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return false;
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date dataNascimento = sdf.parse(dataNascimentoStr);
            
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setDataNascimento(dataNascimento);
            cliente.setEmail(email);
            cliente.setCpf(cpf);
            cliente.setEndereco(endereco);
            cliente.setTelefone(telefone);

            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.cadastrar(cliente);

            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida. Use o formato dd/MM/yyyy.");
            return false;
        }
    }
}
